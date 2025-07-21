package pdev.com.agenda.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoRequest;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoResponse;
import pdev.com.agenda.domain.service.ParecerSocioeconomicoService;
import pdev.com.agenda.domain.service.PdfGeneratorService;

import java.util.List;

@RestController
@RequestMapping("/api/parecer-socioeconomico")
@AllArgsConstructor
public class ParecerSocioeconomicoController {

    private final ParecerSocioeconomicoService service;
    private final PdfGeneratorService pdfGeneratorService;


    @PostMapping
    public ResponseEntity<ParecerSocioeconomicoResponse> criar(@RequestBody ParecerSocioeconomicoRequest request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParecerSocioeconomicoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<ParecerSocioeconomicoResponse>> buscarPorUsuario(@PathVariable Long userId) {
        List<ParecerSocioeconomicoResponse> lista = service.buscarPorUsuarioId(userId);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParecerSocioeconomicoResponse> atualizar(@PathVariable Long id,
                                                                   @RequestBody ParecerSocioeconomicoRequest request) {
        return service.atualizar(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/pdf-base64")
    public ResponseEntity<String> gerarPdfBase64(@PathVariable Long id) {
        try {
            String base64 = pdfGeneratorService.generateParecerPdfBase64(id);
            return ResponseEntity.ok(base64);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
