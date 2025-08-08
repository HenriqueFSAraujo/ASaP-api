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
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.service.FormEnderecoCandidatoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class FormEnderecoCandidatoController {


    private final FormEnderecoCandidatoService enderecoService;

    @PostMapping
    public ResponseEntity<FormEnderecoCandidatoDTO> criarEndereco(@Valid @RequestBody FormEnderecoCandidatoDTO enderecoDTO) {
        FormEnderecoCandidatoDTO novoEndereco = enderecoService.criarEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormEnderecoCandidatoDTO> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody FormEnderecoCandidatoDTO enderecoDTO) {
        FormEnderecoCandidatoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDTO);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormEnderecoCandidatoDTO> buscarPorId(@PathVariable Long id) {
        FormEnderecoCandidatoDTO endereco = enderecoService.buscarPorUserId(id);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<FormEnderecoCandidatoDTO>> listarTodos() {
        List<FormEnderecoCandidatoDTO> enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-cep/{cep}")
    public ResponseEntity<List<FormEnderecoCandidatoDTO>> buscarPorCep(@PathVariable String cep) {
        List<FormEnderecoCandidatoDTO> enderecos = enderecoService.buscarPorCep(cep);
        return ResponseEntity.ok(enderecos);
    }


}

