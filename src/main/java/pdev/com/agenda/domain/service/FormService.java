package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final FormDadosPessoaisMapper dadosPessoaisMapper;

    public FormDadosPessoaisDTO createDadosPessoais(FormDadosPessoaisDTO dto) {
        FormDadosPessoais entity = dadosPessoaisMapper.toEntity(dto);
        entity.setId(null);
        FormDadosPessoais saved = dadosPessoaisRepository.save(entity);
        return dadosPessoaisMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public FormDadosPessoais getFormById(Long id) {
        return dadosPessoaisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Formulário de dados pessoais não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public FormDadosPessoaisDTO getByUserId(Long userId) {
        FormDadosPessoais entity = dadosPessoaisRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Formulário de dados pessoais não encontrado para o usuário: " + userId));
        return dadosPessoaisMapper.toDto(entity);
    }
    @Transactional
    public FormDadosPessoaisDTO updateDadosPessoais(Long id, FormDadosPessoaisDTO dto) {
        FormDadosPessoais existing = dadosPessoaisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados pessoais não encontrados"));

        existing.setFullName(dto.getFullName());
        // ... outros campos

        FormDadosPessoais updated = dadosPessoaisRepository.save(existing);
        return dadosPessoaisMapper.toDto(updated);
    }

    @Transactional
    public FormDadosPessoaisDTO updateByUserId(Long userId, FormDadosPessoaisDTO dto) {
        FormDadosPessoais existing = dadosPessoaisRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dados pessoais não encontrados para o usuário: " + userId));

        existing.setFullName(dto.getFullName());
        // ... outros campos

        FormDadosPessoais updated = dadosPessoaisRepository.save(existing);
        return dadosPessoaisMapper.toDto(updated);
    }


}

