package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.mapper.FormDadosPessoaisMapper;
import pdev.com.agenda.domain.repository.EscolaRepository;
import pdev.com.agenda.domain.repository.FormRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormService {


    private final FormRepository dadosPessoaisRepository;
    private final FormDadosPessoaisMapper dadosPessoaisMapper;
    private final EscolaRepository escolaRepository;

    public FormDadosPessoaisDTO createDadosPessoais(FormDadosPessoaisDTO dto) {
        Long userId = dto.getUserId();
        Optional<FormDadosPessoais> existente = dadosPessoaisRepository.findByUserId(userId);
        FormDadosPessoais entity;
        if (existente.isPresent()) {
            entity = existente.get();
            entity.setFullName(dto.getFullName());
            entity.setEmail(dto.getEmail());
            entity.setCpf(dto.getCpf());
            entity.setRg(dto.getRg());
            entity.setNacionalidade(dto.getNacionalidade());
            entity.setNaturalidade(dto.getNaturalidade());
            entity.setCor(dto.getCor());
            entity.setPhone(dto.getPhone());
            entity.setGender(dto.getGender());
            entity.setCpfBolsista(dto.getCpfBolsista());
            entity.setDataNascimento(dto.getDataNascimento());
            entity.setPcd(dto.getPcd());
            entity.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDENTE");
            entity.setNumEducasenso(dto.getNumEducasenso());
            entity.setEscola(resolveEscola(dto.getEscolaId()));

        } else {
            entity = dadosPessoaisMapper.toEntity(dto);
            entity.setId(null);
            entity.setEscola(resolveEscola(dto.getEscolaId()));
            if (entity.getStatus() == null) {
                entity.setStatus("PENDENTE");
            }
        }

        FormDadosPessoais saved = dadosPessoaisRepository.save(entity);
        return dadosPessoaisMapper.toDto(saved);
    }

    private Escola resolveEscola(Long escolaId) {
        if (escolaId == null) {
            return null;
        }
        return escolaRepository.findById(escolaId)
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com ID: " + escolaId));
    }

    @Transactional(readOnly = true)
    public FormDadosPessoaisDTO getByUserId(Long userId) {
        return dadosPessoaisRepository.findByUserId(userId)
                .map(dadosPessoaisMapper::toDto)
                .orElse(null);
    }

}
