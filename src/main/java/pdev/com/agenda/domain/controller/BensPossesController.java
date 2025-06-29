package pdev.com.agenda.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.BensPossesCompletoDTO;
import pdev.com.agenda.domain.service.BensPossesService;

@RestController
@RequestMapping("/api/bens-posses")
public class BensPossesController {

    private final BensPossesService bensPossesService;

    public BensPossesController(BensPossesService bensPossesService) {
        this.bensPossesService = bensPossesService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody BensPossesCompletoDTO dto) {
        bensPossesService.salvarCompleto(dto);
        return ResponseEntity.ok().build();
    }
}
