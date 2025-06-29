package pdev.com.agenda.domain.service;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaResponse;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;

import pdev.com.agenda.domain.mapper.ProcessoDeBolsaMapper;
import pdev.com.agenda.domain.repository.ProcessoDeBolsaRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessoDeBolsaService {

    private final ProcessoDeBolsaRepository repository;
    private final ProcessoDeBolsaMapper mapper;

    public ProcessoDeBolsaService(ProcessoDeBolsaRepository repository, ProcessoDeBolsaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProcessoDeBolsaResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProcessoDeBolsaResponse findById(Long id) {
        ProcessoDeBolsa entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com ID: " + id));
        return mapper.toResponse(entity);
    }

    public ProcessoDeBolsaResponse create(ProcessoDeBolsaDTO dto) {

        ProcessoDeBolsa entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    public ProcessoDeBolsaResponse update(Long id, ProcessoDeBolsaDTO dto) {
        ProcessoDeBolsa entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com ID: " + id));

        entity.setVaiParticipar(dto.isVaiParticipar());
        entity.setJaFoiContemplado(dto.isJaFoiContemplado());
        entity.setPercentual(dto.getPercentual());

        return mapper.toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Processo não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}

