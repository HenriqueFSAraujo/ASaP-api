package pdev.com.agenda.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import pdev.com.agenda.domain.dto.DocumentoPdfResponse;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdfArquivo;
import pdev.com.agenda.domain.service.DocumentosGeraisPdfService;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-gerais-pdf")
@RequiredArgsConstructor
@Tag(name = "Documentos Gerais PDF", description = "Endpoints para upload e download de PDFs dos documentos gerais")
public class DocumentosGeraisPdfController {

    private final DocumentosGeraisPdfService pdfService;

    @Operation(summary = "Upload de PDF para um campo especifico", description = "Faz o upload de um arquivo PDF para o campo informado de um usuario.")
    @PostMapping("/upload/{campo}")
    public ResponseEntity<String> uploadPdf(
            @Parameter(description = "ID do usuario", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo,
            @Parameter(description = "Arquivo PDF a ser enviado", required = true) @RequestParam("file") MultipartFile file) throws Exception {
        pdfService.salvarPdf(userId, campo, file);
        return ResponseEntity.ok("PDF salvo para o campo: " + campo);
    }

    @Operation(summary = "Download de PDF de um campo especifico", description = "Faz o download do arquivo PDF armazenado no registro informado.")
    @GetMapping("/download/{id}/{campo}")
    public ResponseEntity<byte[]> downloadPdf(
            @Parameter(description = "ID do arquivo PDF", required = true) @PathVariable Long id,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        return pdfService.buscarArquivoPorId(id)
                .filter(arquivo -> campo.equals(arquivo.getTipoDocumento()))
                .map(this::toPdfResponse)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Download de todos os PDFs de um tipo para um usuario", description = "Retorna uma lista de arquivos PDF de um campo especifico para o userId informado.")
    @GetMapping("/download/list/{campo}")
    public ResponseEntity<List<DocumentoPdfResponse>> downloadPdfList(
            @Parameter(description = "ID do usuario", required = true) @RequestParam("userId") Long userId,
            @Parameter(description = "Nome do campo do documento", required = true, example = "singleRegistryRegistration") @PathVariable String campo) {
        List<DocumentoPdfResponse> arquivos = pdfService.listarArquivos(userId, campo);
        if (arquivos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(arquivos);
    }

    private ResponseEntity<byte[]> toPdfResponse(DocumentosGeraisPdfArquivo arquivo) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNomeArquivo() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(arquivo.getConteudo());
    }
}
