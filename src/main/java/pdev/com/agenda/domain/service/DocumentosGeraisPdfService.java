package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.dto.DocumentoPdfResponse;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdfArquivo;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.DocumentosGeraisPdfArquivoRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentosGeraisPdfService {

    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024;

    private static final Set<String> CAMPOS_VALIDOS = Set.of(
            "singleRegistryRegistration",
            "maritalStatus",
            "identityDocuments",
            "guardianshipDocuments",
            "vaccinationCard",
            "proofOfResidence",
            "workContract",
            "bankingRelationsReport",
            "proofOfIncome",
            "supportingDocumentation",
            "bankStatements",
            "businessDocuments",
            "taxDocuments",
            "meiDocuments",
            "healthDisability",
            "familyComposition",
            "governmentProgram"
    );

    private final DocumentosGeraisPdfArquivoRepository arquivoRepository;
    private final UserInfoRepository usuarioRepository;

    @Transactional
    public DocumentosGeraisPdfArquivo salvarPdf(Long userId, String campo, MultipartFile file) throws Exception {
        validarCampo(campo);
        validarArquivo(file);

        UserInfo usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado com ID: " + userId));

        DocumentosGeraisPdfArquivo arquivo = new DocumentosGeraisPdfArquivo();
        arquivo.setUserInfo(usuario);
        arquivo.setTipoDocumento(campo);
        arquivo.setNomeArquivo(resolveNomeArquivo(campo, file));
        arquivo.setMimeType(resolveMimeType(file));
        arquivo.setConteudo(file.getBytes());
        arquivo.setDataUpload(LocalDateTime.now());
        arquivo.setStatus("ATIVO");

        return arquivoRepository.save(arquivo);
    }

    public Optional<DocumentosGeraisPdfArquivo> buscarArquivoPorId(Long id) {
        return arquivoRepository.findById(id);
    }

    public List<DocumentoPdfResponse> listarArquivos(Long userId, String campo) {
        validarCampo(campo);
        return arquivoRepository.findByUserInfoIdAndTipoDocumentoOrderByDataUploadDesc(userId, campo)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public boolean usuarioPossuiDocumentos(Long userId) {
        return arquivoRepository.existsByUserInfoId(userId);
    }

    private DocumentoPdfResponse toResponse(DocumentosGeraisPdfArquivo arquivo) {
        return new DocumentoPdfResponse(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                Base64.getEncoder().encodeToString(arquivo.getConteudo()),
                arquivo.getUserInfo().getId(),
                arquivo.getTipoDocumento(),
                arquivo.getMimeType()
        );
    }

    private void validarCampo(String campo) {
        if (!CAMPOS_VALIDOS.contains(campo)) {
            throw new IllegalArgumentException("Campo invalido: " + campo);
        }
    }

    private void validarArquivo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo PDF e obrigatorio.");
        }
        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new IllegalArgumentException("O arquivo deve ter no maximo 5MB.");
        }

        String contentType = file.getContentType();
        boolean hasPdfContentType = "application/pdf".equalsIgnoreCase(contentType);
        boolean hasPdfExtension = file.getOriginalFilename() != null
                && file.getOriginalFilename().toLowerCase().endsWith(".pdf");

        if (!hasPdfContentType && !hasPdfExtension) {
            throw new IllegalArgumentException("Apenas arquivos PDF sao permitidos.");
        }
    }

    private String resolveNomeArquivo(String campo, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            return campo + ".pdf";
        }
        return originalFilename;
    }

    private String resolveMimeType(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType == null || contentType.isBlank() ? "application/pdf" : contentType;
    }
}
