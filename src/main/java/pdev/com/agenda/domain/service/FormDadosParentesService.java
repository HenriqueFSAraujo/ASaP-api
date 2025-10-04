package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.FormDadosParentesDTO;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.FormDadosParentesMapper;
import pdev.com.agenda.domain.repository.FormDadosParentesRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormDadosParentesService {

    private final FormDadosParentesRepository repository;
    private final FormDadosParentesMapper mapper;

    public List<FormDadosParentesDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public FormDadosParentesDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Dados dos pais não encontrados com ID: " + id));
    }

    @Transactional
    public FormDadosParentesDTO findByUserId(Long userId) {
        return repository.findByUser_Id(userId)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dados dos pais não encontrados para o usuário com ID: " + userId));
    }

    @Transactional
    public FormDadosParentesDTO create(FormDadosParentesDTO dto) {
        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());

        Optional<FormDadosParentes> existente = repository.findByUser(user);

        FormDadosParentes entity;
        if (existente.isPresent()) {
            entity = existente.get();
            entity.setParent1Cpf(dto.getParent1Cpf());
            entity.setParent1FullName(dto.getParent1FullName());
            entity.setParent1Phone(dto.getParent1Phone());
            entity.setParent1MaritalStatus(dto.getParent1MaritalStatus());
            entity.setParent2Cpf(dto.getParent2Cpf());
            entity.setParent2FullName(dto.getParent2FullName());
            entity.setParent2Phone(dto.getParent2Phone());
            entity.setParent2MaritalStatus(dto.getParent2MaritalStatus());
            entity.setResidesWithBothParents(dto.getResidesWithBothParents());
            // O campo user é atualizado apenas se necessário
        } else {
            entity = mapper.toEntity(dto);
            entity.setId(null);
        }
        entity.setStatus("PENDENTE");
        FormDadosParentes savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Transactional
    public FormDadosParentesDTO update(Long id, FormDadosParentesDTO dto) {
        FormDadosParentes existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados dos pais não encontrados com ID: " + id));

        mapper.updateEntityFromDTO(existingEntity, dto);
        existingEntity.setStatus("PENDENTE");
        FormDadosParentes updatedEntity = repository.save(existingEntity);
        return mapper.toDTO(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Dados dos pais não encontrados com ID: " + id);
        }
        repository.deleteById(id);
    }


}
