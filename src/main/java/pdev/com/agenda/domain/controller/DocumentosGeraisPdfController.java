package pdev.com.agenda.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pdev.com.agenda.domain.entity.DocumentoPdf;
import pdev.com.agenda.domain.service.DocumentosGeraisPdfService;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documentos-gerais-pdf")
@AllArgsConstructor
@Tag(name = "Documentos Gerais PDF", description = "Endpoints para upload e download de PDFs dos documentos gerais")
public class DocumentosGeraisPdfController {
    private final DocumentosGeraisPdfService pdfService;

    //http://localhost:8080/api/documentos-gerais-pdf/download/1/singleRegistryRegistration -- exemplo de download
    //localhost:8080/api/documentos-gerais-pdf/upload/singleRegistryRegistration -- exemplo de upload
    @Operation(summary = "Upload de PDF para um campo específico", description = "Faz o upload de um arquivo PDF para o campo informado de um usuário. Cada usuário pode ter até 5 arquivos por campo.")
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

    @Operation(summary = "Download de PDF de um campo específico", description = "Faz o download de um arquivo PDF individual pelo seu ID.")
    @GetMapping("/download/{id}/{campo}")
    public ResponseEntity<byte[]> downloadPdf(
            @Parameter(description = "ID do documento", required = true) @PathVariable Long id,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        return pdfService.buscarPorId(id)
                .filter(documento -> campo.equals(documento.getTipoDocumento()))
                .map(documento -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivoOuPadrao(documento) + "\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(documento.getConteudo()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Download de todos os PDFs de um tipo para um usuário", description = "Retorna uma lista com todos os arquivos PDF de um campo específico enviados pelo userId informado.")
    @GetMapping("/download/list/{campo}")
    public ResponseEntity<List<Map<String, Object>>> downloadPdfList(
            @Parameter(description = "ID do usuário", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        List<DocumentoPdf> documentos = pdfService.buscarPorUserIdETipo(userId, campo);
        List<Map<String, Object>> arquivos = documentos.stream()
                .map(documento -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", documento.getId());
                    map.put("nomeArquivo", nomeArquivoOuPadrao(documento));
                    map.put("conteudoBase64", Base64.getEncoder().encodeToString(documento.getConteudo()));
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(arquivos);
    }

    private String nomeArquivoOuPadrao(DocumentoPdf documento) {
        return documento.getNomeArquivo() != null
                ? documento.getNomeArquivo()
                : documento.getTipoDocumento() + "_" + documento.getId() + ".pdf";
    }
}
