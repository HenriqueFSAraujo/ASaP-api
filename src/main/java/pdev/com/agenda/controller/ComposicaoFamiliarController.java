package pdev.com.agenda.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.UserInfoWithStatusDTO;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.service.UserInfoService;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.dto.ComposicaoFamiliarRequestDTO;
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
    private final UserInfoService userInfoService;

    @ApiResponse(responseCode = "201", description = "Composições familiares criadas com sucesso para o usuário")
    @PostMapping
    public ResponseEntity<List<ComposicaoFamiliarDTO>> saveAllForUser(@RequestBody ComposicaoFamiliarRequestDTO request) {
        List<ComposicaoFamiliarDTO> savedComposicoesDTO = service.saveAllForUser(request);
        return new ResponseEntity<>(savedComposicoesDTO, HttpStatus.CREATED);
    }
}
