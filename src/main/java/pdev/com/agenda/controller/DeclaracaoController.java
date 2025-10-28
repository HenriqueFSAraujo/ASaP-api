package pdev.com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.dto.DeclaracaoDTO;
import pdev.com.agenda.service.DeclaracaoService;

import java.util.List;

@RestController
@RequestMapping("/api/declaracoes")
public class DeclaracaoController {

    @Autowired
    private DeclaracaoService declaracaoService;

    @GetMapping
    public ResponseEntity<List<DeclaracaoDTO>> getAllDeclaracoes() {
        List<DeclaracaoDTO> declaracoes = declaracaoService.findAll();
        return ResponseEntity.ok(declaracoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclaracaoDTO> getDeclaracaoById(@PathVariable Long id) {
        DeclaracaoDTO declaracao = declaracaoService.findById(id);
        if (declaracao != null) {
            return ResponseEntity.ok(declaracao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DeclaracaoDTO> createDeclaracao(@RequestBody DeclaracaoDTO declaracaoDTO) {
        DeclaracaoDTO createdDeclaracao = declaracaoService.save(declaracaoDTO);
        return ResponseEntity.ok(createdDeclaracao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeclaracao(@PathVariable Long id) {
        declaracaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DeclaracaoDTO>> getDeclaracoesByUserId(@PathVariable Long userId) {
        List<DeclaracaoDTO> declaracoes = declaracaoService.findByUserId(userId);
        return ResponseEntity.ok(declaracoes);
    }
}
