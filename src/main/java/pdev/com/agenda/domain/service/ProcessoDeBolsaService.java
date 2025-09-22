package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaResponse;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.ProcessoDeBolsaMapper;
import pdev.com.agenda.domain.repository.ProcessoDeBolsaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessoDeBolsaService {

    private final ProcessoDeBolsaRepository repository;
    private final ProcessoDeBolsaMapper mapper;


    public List<ProcessoDeBolsaResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProcessoDeBolsaResponse findByUserId(Long id) {
        ProcessoDeBolsa entity = repository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com ID: " + id));
        return mapper.toResponse(entity);
    }

    public ProcessoDeBolsaResponse create(ProcessoDeBolsaDTO dto) {
        Long userId = dto.getUserId();
        UserInfo user = new UserInfo();
        user.setId(userId);
        Optional<ProcessoDeBolsa> existente = repository.findByUserId(user.getId());

        ProcessoDeBolsa entity;
        if (existente.isPresent()) {
            entity = existente.get();

            entity.setVaiParticipar(dto.isVaiParticipar());
            entity.setJaFoiContemplado(dto.isJaFoiContemplado());
            entity.setPercentual(dto.getPercentual());

        } else {
            entity = mapper.toEntity(dto);
        }
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
