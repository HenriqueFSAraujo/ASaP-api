package pdev.com.agenda.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;
import pdev.com.agenda.domain.service.DocumentosGeraisPdfService;

import java.util.Optional;

@RestController
@RequestMapping("/api/documentos-gerais-pdf")
@AllArgsConstructor
@Tag(name = "Documentos Gerais PDF", description = "Endpoints para upload e download de PDFs dos documentos gerais")
public class DocumentosGeraisPdfController {
    private final DocumentosGeraisPdfService pdfService;

    @Operation(summary = "Upload de PDF para um campo específico", description = "Faz o upload de um arquivo PDF para o campo informado de um usuário.")
    @PostMapping("/upload/{campo}")
    public ResponseEntity<String> uploadPdf(
            @Parameter(description = "ID do usuário", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo,
            @Parameter(description = "Arquivo PDF a ser enviado", required = true) @RequestParam("file") MultipartFile file) {
        try {
            pdfService.salvarPdf(userId, campo, file);
            return ResponseEntity.ok("PDF salvo para o campo: " + campo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar PDF: " + e.getMessage());
        }
    }

    @Operation(summary = "Download de PDF de um campo específico", description = "Faz o download do arquivo PDF armazenado em um campo específico do registro informado.")
    @GetMapping("/download/{id}/{campo}")
    public ResponseEntity<byte[]> downloadPdf(
            @Parameter(description = "ID do registro de documentos gerais PDF", required = true) @PathVariable Long id,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        Optional<DocumentosGeraisPdf> pdfOpt = pdfService.buscarPorId(id);
        if (pdfOpt.isPresent()) {
            DocumentosGeraisPdf pdf = pdfOpt.get();
            byte[] conteudo = null;
            String nomeArquivo = campo + ".pdf";
            switch (campo) {
                case "singleRegistryRegistration": conteudo = pdf.getSingleRegistryRegistration(); break;
                case "maritalStatus": conteudo = pdf.getMaritalStatus(); break;
                case "identityDocuments": conteudo = pdf.getIdentityDocuments(); break;
                case "guardianshipDocuments": conteudo = pdf.getGuardianshipDocuments(); break;
                case "vaccinationCard": conteudo = pdf.getVaccinationCard(); break;
                case "proofOfResidence": conteudo = pdf.getProofOfResidence(); break;
                case "workContract": conteudo = pdf.getWorkContract(); break;
                case "bankingRelationsReport": conteudo = pdf.getBankingRelationsReport(); break;
                case "proofOfIncome": conteudo = pdf.getProofOfIncome(); break;
                case "supportingDocumentation": conteudo = pdf.getSupportingDocumentation(); break;
                case "bankStatements": conteudo = pdf.getBankStatements(); break;
                case "businessDocuments": conteudo = pdf.getBusinessDocuments(); break;
                case "taxDocuments": conteudo = pdf.getTaxDocuments(); break;
                case "meiDocuments": conteudo = pdf.getMeiDocuments(); break;
                case "healthDisability": conteudo = pdf.getHealthDisability(); break;
                case "familyComposition": conteudo = pdf.getFamilyComposition(); break;
                case "governmentProgram": conteudo = pdf.getGovernmentProgram(); break;
                default: return ResponseEntity.badRequest().build();
            }
            if (conteudo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(conteudo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
