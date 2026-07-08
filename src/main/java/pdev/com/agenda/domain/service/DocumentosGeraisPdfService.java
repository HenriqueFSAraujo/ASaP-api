package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentoPdf;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.DocumentoPdfRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DocumentosGeraisPdfService {

    /** Mesmo limite anunciado na UI ("Max. 5 documentos, 5MB cada"). */
    private static final int MAX_ARQUIVOS_POR_TIPO = 5;

    private static final Set<String> TIPOS_VALIDOS = Set.of(
            "singleRegistryRegistration", "maritalStatus", "identityDocuments",
            "guardianshipDocuments", "vaccinationCard", "proofOfResidence",
            "workContract", "bankingRelationsReport", "proofOfIncome",
            "supportingDocumentation", "bankStatements", "businessDocuments",
            "taxDocuments", "meiDocuments", "healthDisability",
            "familyComposition", "governmentProgram"
    );

    private final DocumentoPdfRepository documentoPdfRepository;
    private final UserInfoRepository usuarioRepository;

    @Transactional
    public DocumentoPdf salvarPdf(Long userId, String campo, MultipartFile file) throws IOException {
        if (!TIPOS_VALIDOS.contains(campo)) {
            throw new IllegalArgumentException("Campo inválido: " + campo);
        }
        UserInfo usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        long existentes = documentoPdfRepository.countByUserInfoIdAndTipoDocumento(userId, campo);
        if (existentes >= MAX_ARQUIVOS_POR_TIPO) {
            throw new IllegalArgumentException(
                    "Limite de " + MAX_ARQUIVOS_POR_TIPO + " documentos para o campo '" + campo + "' já foi atingido");
        }

        DocumentoPdf documento = new DocumentoPdf();
        documento.setUserInfo(usuario);
        documento.setTipoDocumento(campo);
        documento.setNomeArquivo(file.getOriginalFilename());
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
        return documentoPdfRepository.findAllByUserInfoIdAndTipoDocumento(userId, campo);
    }
}
