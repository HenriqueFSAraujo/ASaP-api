package pdev.com.agenda.domain.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaResponse;
import pdev.com.agenda.domain.service.ProcessoDeBolsaService;

import java.util.List;

@RestController
@RequestMapping("/api/processo-bolsas")
public class ProcessoDeBolsaController {

    private final ProcessoDeBolsaService service;

    public ProcessoDeBolsaController(ProcessoDeBolsaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDeBolsaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProcessoDeBolsaResponse> create(@RequestBody ProcessoDeBolsaDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoDeBolsaResponse> update(@PathVariable Long id, @RequestBody ProcessoDeBolsaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

