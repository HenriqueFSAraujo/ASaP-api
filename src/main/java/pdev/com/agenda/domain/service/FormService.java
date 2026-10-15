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

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormService {


    private final FormRepository dadosPessoaisRepository;
    private final FormDadosPessoaisMapper dadosPessoaisMapper;
    private final EscolaRepository escolaRepository;

    public FormDadosPessoaisDTO createDadosPessoais(FormDadosPessoaisDTO dto) {
        Long userId = dto.userId();
        Optional<FormDadosPessoais> existente = dadosPessoaisRepository.findByUserId(userId);
        FormDadosPessoais entity;
        if (existente.isPresent()) {
            entity = existente.get();
            entity.setFullName(dto.fullName());
            entity.setEmail(dto.email());
            entity.setCpf(dto.cpf());
            entity.setRg(dto.rg());
            entity.setNacionalidade(dto.nacionalidade());
            entity.setNaturalidade(dto.naturalidade());
            entity.setCor(dto.cor());
            entity.setPhone(dto.phone());
            entity.setGender(dto.gender());
            entity.setCpfBolsista(dto.cpfBolsista());
            entity.setDataNascimento(dto.dataNascimento());
            entity.setPcd(dto.pcd());
            entity.setStatus(dto.status() != null ? dto.status() : "PENDENTE");
            entity.setNumEducasenso(dto.numEducasenso());
            entity.setEscola(resolveEscola(dto.escolaId()));

        } else {
            entity = dadosPessoaisMapper.toEntity(dto);
            entity.setId(null);
            entity.setEscola(resolveEscola(dto.escolaId()));
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
