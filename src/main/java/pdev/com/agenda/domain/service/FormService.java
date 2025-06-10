package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.mapper.FormDadosPessoaisMapper;
import pdev.com.agenda.domain.mapper.FormEnderecoCandidatoMapper;
import pdev.com.agenda.domain.repository.FormEnderecoCandidatoRepository;
import pdev.com.agenda.domain.repository.FormRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class FormService {


    private final FormRepository dadosPessoaisRepository;
    private final FormEnderecoCandidatoRepository enderecoRepository;
    private final FormDadosPessoaisMapper dadosPessoaisMapper;
    private final FormEnderecoCandidatoMapper enderecoMapper;

    public FormDadosPessoaisDTO createDadosPessoais(FormDadosPessoaisDTO dto) {
        FormDadosPessoais entity = dadosPessoaisMapper.toEntity(dto);
        entity.setId(null);
        FormDadosPessoais saved = dadosPessoaisRepository.save(entity);
        return dadosPessoaisMapper.toDto(saved);
    }

    public FormDadosPessoais getFormById(Long id) {
        return dadosPessoaisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário de dados dos pais não encontrado com ID: " + id));
    }

    public FormDadosPessoaisDTO updateDadosPessoais(Long id, FormDadosPessoaisDTO dto) {
        FormDadosPessoais existing = dadosPessoaisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados pessoais não encontrados"));

        existing.setFullName(dto.getFullName());
        existing.setLogin(dto.getLogin());

        FormDadosPessoais updated = dadosPessoaisRepository.save(existing);
        return dadosPessoaisMapper.toDto(updated);
    }


}

