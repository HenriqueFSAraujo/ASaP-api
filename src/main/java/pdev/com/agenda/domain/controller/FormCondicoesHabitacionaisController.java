package pdev.com.agenda.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.service.FormCondicoesHabitacionaisService;

import java.util.List;

@RestController
@RequestMapping("/api/form-condicoes-habitacionais")
@AllArgsConstructor
public class FormCondicoesHabitacionaisController {


    private FormCondicoesHabitacionaisService service;


    @PostMapping
    public ResponseEntity<CondicoesHabitacionaisDTO> create(@RequestBody CondicoesHabitacionaisDTO dto) {
        CondicoesHabitacionaisDTO created = service.salvarOuAtualizar(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormCondicoesHabitacionais> getById(@PathVariable Long id) {
        return service.getByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<FormCondicoesHabitacionais>> getAll() {
        List<FormCondicoesHabitacionais> list = service.getAll();
        return ResponseEntity.ok(list);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FormCondicoesHabitacionais> update(@PathVariable Long id, @RequestBody CondicoesHabitacionaisDTO dto) {
        FormCondicoesHabitacionais updated = service.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
