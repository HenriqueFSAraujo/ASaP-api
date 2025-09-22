package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.mapper.FormDadosPessoaisMapper;
import pdev.com.agenda.domain.repository.FormRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormService {


    private final FormRepository dadosPessoaisRepository;
    private final FormDadosPessoaisMapper dadosPessoaisMapper;

    public FormDadosPessoaisDTO createDadosPessoais(FormDadosPessoaisDTO dto) {
        Long userId = dto.getUserId();

        Optional<FormDadosPessoais> existente = dadosPessoaisRepository.findByUserId(userId);
        FormDadosPessoais entity;
        if (existente.isPresent()) {
            entity = existente.get();
            // Atualiza todos os campos necessários
            entity.setFullName(dto.getFullName());
            entity.setEmail(dto.getEmail());
            entity.setCpf(dto.getCpf());
            entity.setCpfBolsista(dto.getCpfBolsista());
            entity.setPhone(dto.getPhone());
            entity.setGender(dto.getGender());
            entity.setDataNascimento(dto.getDataNascimento());
            entity.setPcd(dto.getPcd());
            entity.setNumEducasenso(dto.getNumEducasenso());

        } else {
            entity = dadosPessoaisMapper.toEntity(dto);
            entity.setId(null);
        }
        FormDadosPessoais saved = dadosPessoaisRepository.save(entity);
        return dadosPessoaisMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public FormDadosPessoaisDTO getByUserId(Long userId) {
        FormDadosPessoais entity = dadosPessoaisRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Formulário de dados pessoais não encontrado para o usuário: " + userId));
        return dadosPessoaisMapper.toDto(entity);
    }
}
