package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.DocumentosGeraisPdfRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentosGeraisPdfService {

    private final DocumentosGeraisPdfRepository pdfRepository;
    private final UserInfoRepository usuarioRepository;

    @Transactional
    public DocumentosGeraisPdf salvarPdf(Long userId, String campo, MultipartFile file) throws Exception {
        Optional<UserInfo> usuarioOpt = usuarioRepository.findById(userId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        // Upsert por user_id (cada usuário tem no máximo 1 registro de documentos_gerais_pdf).
        // Antes: findById(userId) usava o userId como PK da tabela documentos_gerais_pdf, o que
        // criava registros novos a cada upload e podia sobrescrever documentos de outro usuário.
        DocumentosGeraisPdf pdf = pdfRepository.findByUserInfoId(userId).orElse(new DocumentosGeraisPdf());
        pdf.setUserInfo(usuarioOpt.get());
        pdf.setDataUpload(LocalDateTime.now());
        byte[] conteudo = file.getBytes();
        switch (campo) {
            case "singleRegistryRegistration": pdf.setSingleRegistryRegistration(conteudo); break;
            case "maritalStatus": pdf.setMaritalStatus(conteudo); break;
            case "identityDocuments": pdf.setIdentityDocuments(conteudo); break;
            case "guardianshipDocuments": pdf.setGuardianshipDocuments(conteudo); break;
            case "vaccinationCard": pdf.setVaccinationCard(conteudo); break;
            case "proofOfResidence": pdf.setProofOfResidence(conteudo); break;
            case "workContract": pdf.setWorkContract(conteudo); break;
            case "bankingRelationsReport": pdf.setBankingRelationsReport(conteudo); break;
            case "proofOfIncome": pdf.setProofOfIncome(conteudo); break;
            case "supportingDocumentation": pdf.setSupportingDocumentation(conteudo); break;
            case "bankStatements": pdf.setBankStatements(conteudo); break;
            case "businessDocuments": pdf.setBusinessDocuments(conteudo); break;
            case "taxDocuments": pdf.setTaxDocuments(conteudo); break;
            case "meiDocuments": pdf.setMeiDocuments(conteudo); break;
            case "healthDisability": pdf.setHealthDisability(conteudo); break;
            case "familyComposition": pdf.setFamilyComposition(conteudo); break;
            case "governmentProgram": pdf.setGovernmentProgram(conteudo); break;
            default: throw new IllegalArgumentException("Campo inválido");
        }
        pdf.setStatus("ATIVO");
        return pdfRepository.save(pdf);
    }

    public Optional<DocumentosGeraisPdf> buscarPorId(Long id) {
        return pdfRepository.findById(id);
    }

    public List<DocumentosGeraisPdf> buscarPorUserId(Long userId) {
        return pdfRepository.findAllByUserInfoId(userId);
    }
}
