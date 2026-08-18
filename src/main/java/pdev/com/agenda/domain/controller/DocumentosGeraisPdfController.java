package pdev.com.agenda.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequiredArgsConstructor
@Tag(name = "Documentos Gerais PDF", description = "Endpoints para upload e download de PDFs dos documentos gerais")
public class DocumentosGeraisPdfController {

    private final DocumentosGeraisPdfService pdfService;

    @Operation(summary = "Upload de PDF para um campo especifico", description = "Faz o upload de um arquivo PDF para o campo informado de um usuario. Cada usuario pode ter ate 5 arquivos por campo.")
    @PostMapping("/upload/{campo}")
    public ResponseEntity<String> uploadPdf(
            @Parameter(description = "ID do usuario", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo,
            @Parameter(description = "Arquivo PDF a ser enviado", required = true) @RequestParam("file") MultipartFile file) throws Exception {
        pdfService.salvarPdf(userId, campo, file);
        return ResponseEntity.ok("PDF salvo para o campo: " + campo);
    }

    @Operation(summary = "Download de PDF de um campo especifico", description = "Faz o download de um arquivo PDF individual pelo seu ID.")
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

    @Operation(summary = "Download de todos os PDFs de um tipo para um usuario", description = "Retorna uma lista com todos os arquivos PDF de um campo especifico enviados pelo userId informado.")
    @GetMapping("/download/list/{campo}")
    public ResponseEntity<List<Map<String, Object>>> downloadPdfList(
            @Parameter(description = "ID do usuario", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        List<DocumentoPdf> documentos = pdfService.buscarPorUserIdETipo(userId, campo);
        List<Map<String, Object>> arquivos = documentos.stream()
                .map(documento -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", documento.getId());
                    map.put("nomeArquivo", nomeArquivoOuPadrao(documento));
                    map.put("conteudoBase64", Base64.getEncoder().encodeToString(documento.getConteudo()));
                    map.put("userId", documento.getUserInfo().getId());
                    map.put("documentType", documento.getTipoDocumento());
                    map.put("mimeType", MediaType.APPLICATION_PDF_VALUE);
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(arquivos);
    }

    @Operation(summary = "Remove um documento PDF", description = "Apaga um arquivo individual pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPdf(@Parameter(description = "ID do documento", required = true) @PathVariable Long id) {
        if (pdfService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pdfService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    private String nomeArquivoOuPadrao(DocumentoPdf documento) {
        return documento.getNomeArquivo() != null
                ? documento.getNomeArquivo()
                : documento.getTipoDocumento() + "_" + documento.getId() + ".pdf";
    }
}
