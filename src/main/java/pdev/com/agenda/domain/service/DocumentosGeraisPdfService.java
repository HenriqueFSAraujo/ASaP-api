package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentoPdf;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.DocumentoPdfRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentosGeraisPdfService {

    private static final int MAX_ARQUIVOS_POR_TIPO = 5;
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024;

    private static final Set<String> TIPOS_VALIDOS = Set.of(
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

    private final DocumentoPdfRepository documentoPdfRepository;
    private final UserInfoRepository usuarioRepository;

    @Transactional
    public DocumentoPdf salvarPdf(Long userId, String campo, MultipartFile file) throws IOException {
        validarCampo(campo);
        validarArquivo(file);

        UserInfo usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado com ID: " + userId));

        long existentes = documentoPdfRepository.countByUserInfoIdAndTipoDocumento(userId, campo);
        if (existentes >= MAX_ARQUIVOS_POR_TIPO) {
            throw new IllegalArgumentException(
                    "Limite de " + MAX_ARQUIVOS_POR_TIPO + " documentos para o campo '" + campo + "' ja foi atingido");
        }

        DocumentoPdf documento = new DocumentoPdf();
        documento.setUserInfo(usuario);
        documento.setTipoDocumento(campo);
        documento.setNomeArquivo(resolveNomeArquivo(campo, file));
        documento.setConteudo(file.getBytes());
        documento.setDataUpload(LocalDateTime.now());
        documento.setStatus("ATIVO");

        return documentoPdfRepository.save(documento);
    }

    public Optional<DocumentoPdf> buscarPorId(Long id) {
        return documentoPdfRepository.findById(id);
    }

    public List<DocumentoPdf> buscarPorUserId(Long userId) {
        return documentoPdfRepository.findAllByUserInfoId(userId);
    }

    public List<DocumentoPdf> buscarPorUserIdETipo(Long userId, String campo) {
        validarCampo(campo);
        return documentoPdfRepository.findAllByUserInfoIdAndTipoDocumento(userId, campo);
    }

    public void deletarPorId(Long id) {
        documentoPdfRepository.deleteById(id);
    }

    private void validarCampo(String campo) {
        if (!TIPOS_VALIDOS.contains(campo)) {
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
}
