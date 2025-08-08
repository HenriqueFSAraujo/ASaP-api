package pdev.com.agenda.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.FormDadosParentesDTO;
import pdev.com.agenda.domain.service.FormDadosParentesService;

@RestController
@RequestMapping("/api/parentes")
@RequiredArgsConstructor
public class FormDadosParentesController {

    private final FormDadosParentesService service;



    @GetMapping("/{id}")
    public ResponseEntity<FormDadosParentesDTO> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FormDadosParentesDTO> create(@RequestBody FormDadosParentesDTO createDTO) {
        return ResponseEntity.ok(service.create(createDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormDadosParentesDTO> update(@PathVariable Long id, @RequestBody FormDadosParentesDTO updateDTO) {
        return ResponseEntity.ok(service.update(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
