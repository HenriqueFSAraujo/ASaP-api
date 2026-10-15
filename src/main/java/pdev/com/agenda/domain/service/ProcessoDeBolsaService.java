package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.ProcessoDeBolsaMapper;
import pdev.com.agenda.domain.repository.ProcessoDeBolsaRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessoDeBolsaService {

    private final ProcessoDeBolsaRepository repository;
    private final ProcessoDeBolsaMapper mapper;


    public List<ProcessoDeBolsaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProcessoDeBolsaDTO findByUserId(Long id) {
        return repository.findByUser_Id(id)
                .map(mapper::toResponse)
                .orElse(null);
    }

    public ProcessoDeBolsaDTO create(ProcessoDeBolsaDTO dto) {
        Long userId = dto.userId();
        UserInfo user = new UserInfo();
        user.setId(userId);
        Optional<ProcessoDeBolsa> existente = repository.findByUser_Id(user.getId());

        ProcessoDeBolsa entity;
        if (existente.isPresent()) {
            entity = existente.get();
            entity.setVaiParticipar(dto.vaiParticipar());
            entity.setJaFoiContemplado(dto.jaFoiContemplado());
            entity.setPercentual(dto.percentual());
            entity.setSegmentoAno(dto.segmentoAno());
            entity.setSerieAno(dto.serieAno());
        } else {
            entity = mapper.toEntity(dto);
        }
        entity.setUser(user);
        repository.save(entity);
        return mapper.toResponse(entity);
    }
    public ProcessoDeBolsaDTO update(Long id, ProcessoDeBolsaDTO dto) {
        ProcessoDeBolsa entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com ID: " + id));

        entity.setVaiParticipar(dto.vaiParticipar());
        entity.setJaFoiContemplado(dto.jaFoiContemplado());
        entity.setPercentual(dto.percentual());
        entity.setSegmentoAno(dto.segmentoAno());
        entity.setSerieAno(dto.serieAno());
        entity.setStatus("PENDENTE");
        return mapper.toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Processo não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}
