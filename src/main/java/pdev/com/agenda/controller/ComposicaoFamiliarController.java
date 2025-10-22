package pdev.com.agenda.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.UserInfo;

import pdev.com.agenda.domain.service.UserInfoService;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.dto.ComposicaoFamiliarRequestDTO;
import pdev.com.agenda.mapper.ComposicaoFamiliarMapper;
import pdev.com.agenda.repository.ComposicaoFamiliarRepository;
import pdev.com.agenda.service.ComposicaoFamiliarService;


import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/composicao-familiar")
@RequiredArgsConstructor
@Tag(name = "ComposicaoFamiliar", description = "API para gerenciar composições familiares")
public class ComposicaoFamiliarController {

    private final ComposicaoFamiliarService service;
    private final ComposicaoFamiliarService composicaoFamiliarRepository;

    @ApiResponse(responseCode = "201", description = "Composições familiares criadas com sucesso para o usuário")
    @PostMapping
    public ResponseEntity<List<ComposicaoFamiliarDTO>> saveAllForUser(@RequestBody ComposicaoFamiliarRequestDTO request) {
        Long userId = request.getUserInfoId();
        List<ComposicaoFamiliarDTO> composicoesDTO = request.getComposicaoFamiliar();
        List<ComposicaoFamiliar> composicoes = ComposicaoFamiliarMapper.INSTANCE.toEntityList(composicoesDTO);
        composicoes.forEach(composicao -> composicao.setUserInfo(new UserInfo(userId)));
        List<ComposicaoFamiliar> savedComposicoes = service.saveAll(composicoes);
        List<ComposicaoFamiliarDTO> savedComposicoesDTO = ComposicaoFamiliarMapper.INSTANCE.toDTOList(savedComposicoes);
        return new ResponseEntity<>(savedComposicoesDTO, HttpStatus.CREATED);
    }
}
