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
import java.util.Comparator;
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
        //
        // Self-healing: usuários que já tinham mais de um registro (criado antes deste upsert
        // existir) fariam `findByUserInfoId` (que espera resultado único) lançar
        // NonUniqueResultException. Aqui detectamos e mesclamos as duplicatas na mais recente
        // antes de continuar, sem perder documentos já enviados anteriormente.
        List<DocumentosGeraisPdf> existentes = pdfRepository.findAllByUserInfoId(userId);
        existentes.sort(Comparator.comparingLong(DocumentosGeraisPdf::getId).reversed());
        DocumentosGeraisPdf pdf;
        if (existentes.isEmpty()) {
            pdf = new DocumentosGeraisPdf();
        } else {
            pdf = existentes.get(0);
            if (existentes.size() > 1) {
                List<DocumentosGeraisPdf> duplicadas = existentes.subList(1, existentes.size());
                duplicadas.forEach(duplicada -> mesclarDocumentos(pdf, duplicada));
                pdfRepository.deleteAll(duplicadas);
            }
        }
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

    /** Copia para {@code canonica} os campos de {@code duplicada} que ainda estiverem vazios. */
    private void mesclarDocumentos(DocumentosGeraisPdf canonica, DocumentosGeraisPdf duplicada) {
        if (canonica.getSingleRegistryRegistration() == null) canonica.setSingleRegistryRegistration(duplicada.getSingleRegistryRegistration());
        if (canonica.getMaritalStatus() == null) canonica.setMaritalStatus(duplicada.getMaritalStatus());
        if (canonica.getIdentityDocuments() == null) canonica.setIdentityDocuments(duplicada.getIdentityDocuments());
        if (canonica.getGuardianshipDocuments() == null) canonica.setGuardianshipDocuments(duplicada.getGuardianshipDocuments());
        if (canonica.getVaccinationCard() == null) canonica.setVaccinationCard(duplicada.getVaccinationCard());
        if (canonica.getProofOfResidence() == null) canonica.setProofOfResidence(duplicada.getProofOfResidence());
        if (canonica.getWorkContract() == null) canonica.setWorkContract(duplicada.getWorkContract());
        if (canonica.getBankingRelationsReport() == null) canonica.setBankingRelationsReport(duplicada.getBankingRelationsReport());
        if (canonica.getProofOfIncome() == null) canonica.setProofOfIncome(duplicada.getProofOfIncome());
        if (canonica.getSupportingDocumentation() == null) canonica.setSupportingDocumentation(duplicada.getSupportingDocumentation());
        if (canonica.getBankStatements() == null) canonica.setBankStatements(duplicada.getBankStatements());
        if (canonica.getBusinessDocuments() == null) canonica.setBusinessDocuments(duplicada.getBusinessDocuments());
        if (canonica.getTaxDocuments() == null) canonica.setTaxDocuments(duplicada.getTaxDocuments());
        if (canonica.getMeiDocuments() == null) canonica.setMeiDocuments(duplicada.getMeiDocuments());
        if (canonica.getHealthDisability() == null) canonica.setHealthDisability(duplicada.getHealthDisability());
        if (canonica.getFamilyComposition() == null) canonica.setFamilyComposition(duplicada.getFamilyComposition());
        if (canonica.getGovernmentProgram() == null) canonica.setGovernmentProgram(duplicada.getGovernmentProgram());
    }
}
