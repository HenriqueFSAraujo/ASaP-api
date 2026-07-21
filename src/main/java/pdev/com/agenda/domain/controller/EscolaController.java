package pdev.com.agenda.domain.controller;

import lombok.RequiredArgsConstructor;
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
import pdev.com.agenda.domain.dto.EscolaDTO;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.domain.service.EscolaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/escolas")
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService escolaService;

    @GetMapping
    public ResponseEntity<List<EscolaDTO>> findAll() {
        return ResponseEntity.ok(escolaService.findAll());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<EscolaDTO>> findByTipo(@PathVariable TipoEscolaEnum tipo) {
        return ResponseEntity.ok(escolaService.findByTipo(tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(escolaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EscolaDTO> create(@Valid @RequestBody EscolaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(escolaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolaDTO> update(@PathVariable Long id, @Valid @RequestBody EscolaDTO dto) {
        return ResponseEntity.ok(escolaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        escolaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
