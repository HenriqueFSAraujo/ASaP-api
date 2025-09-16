package pdev.com.agenda.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;
import pdev.com.agenda.domain.entity.Usuario;
import pdev.com.agenda.domain.repository.DocumentosGeraisPdfRepository;
import pdev.com.agenda.domain.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DocumentosGeraisPdfService {
    private final DocumentosGeraisPdfRepository pdfRepository;
    private final UsuarioRepository usuarioRepository;

    public DocumentosGeraisPdfService(DocumentosGeraisPdfRepository pdfRepository, UsuarioRepository usuarioRepository) {
        this.pdfRepository = pdfRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public DocumentosGeraisPdf salvarPdf(Long userId, String campo, MultipartFile file) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(userId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        DocumentosGeraisPdf pdf = pdfRepository.findById(userId).orElse(new DocumentosGeraisPdf());
        pdf.setUsuario(usuarioOpt.get());
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
        return pdfRepository.save(pdf);
    }

    public Optional<DocumentosGeraisPdf> buscarPorId(Long id) {
        return pdfRepository.findById(id);
    }
}


