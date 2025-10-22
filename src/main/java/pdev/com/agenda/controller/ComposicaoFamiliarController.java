package pdev.com.agenda.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.mapper.ComposicaoFamiliarMapper;
import pdev.com.agenda.service.ComposicaoFamiliarService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/composicao-familiar")
@RequiredArgsConstructor
@Tag(name = "ComposicaoFamiliar", description = "API para gerenciar composições familiares")
public class ComposicaoFamiliarController {

    private final ComposicaoFamiliarService service;

    @ApiResponse(responseCode = "201", description = "Composições familiares criadas com sucesso")
    @PostMapping
    public ResponseEntity<List<ComposicaoFamiliarDTO>> saveAll(@RequestBody List<ComposicaoFamiliarDTO> composicoesDTO) {
        List<ComposicaoFamiliar> composicoes = ComposicaoFamiliarMapper.INSTANCE.toEntityList(composicoesDTO);
        List<ComposicaoFamiliar> savedComposicoes = service.saveAll(composicoes);
        List<ComposicaoFamiliarDTO> savedComposicoesDTO = ComposicaoFamiliarMapper.INSTANCE.toDTOList(savedComposicoes);
        return new ResponseEntity<>(savedComposicoesDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "Lista de composições familiares retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ComposicaoFamiliarDTO>> getAll() {
        List<ComposicaoFamiliar> composicoes = service.findAll();
        List<ComposicaoFamiliarDTO> composicoesDTO = ComposicaoFamiliarMapper.INSTANCE.toDTOList(composicoes);
        return new ResponseEntity<>(composicoesDTO, HttpStatus.OK);
    }
}
