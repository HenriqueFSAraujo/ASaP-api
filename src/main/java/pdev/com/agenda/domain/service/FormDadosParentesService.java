package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.FormDadosParentesDTO;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.mapper.FormDadosParentesMapper;
import pdev.com.agenda.domain.repository.FormDadosParentesRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
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
    public FormDadosParentesDTO create(FormDadosParentesDTO dto) {
        FormDadosParentes entity = mapper.toEntity(dto);
        entity.setId(null); // Garantir que é uma nova entidade
        FormDadosParentes savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Transactional
    public FormDadosParentesDTO update(Long id, FormDadosParentesDTO dto) {
        FormDadosParentes existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados dos pais não encontrados com ID: " + id));

        mapper.updateEntityFromDTO(existingEntity, dto);
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



    public List<FormDadosParentesDTO> findByParent1Cpf(String cpf) {
        return repository.findByParent1Cpf(cpf)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

}
