package pdev.com.agenda.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.BensPossesCompletoDTO;
import pdev.com.agenda.domain.entity.BensPosses;
import pdev.com.agenda.domain.service.BensPossesService;

@RestController
@RequestMapping("/api/bens-posses")
@AllArgsConstructor
public class BensPossesController {

    private final BensPossesService bensPossesService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody BensPossesCompletoDTO dto) {
        bensPossesService.salvarCompleto(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public BensPosses getByUser(@PathVariable Long userId) {
        return bensPossesService.buscarPorUserId(userId);
    }
}
