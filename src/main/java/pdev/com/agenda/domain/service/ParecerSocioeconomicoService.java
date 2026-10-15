package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoDTO;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;
import pdev.com.agenda.domain.mapper.ParecerSocioeconomicoMapper;
import pdev.com.agenda.domain.repository.ParecerSocioeconomicoRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParecerSocioeconomicoService {

    private final ParecerSocioeconomicoRepository repository;
    private final ParecerSocioeconomicoMapper mapper;


    @Transactional
    public ParecerSocioeconomicoDTO salvar(ParecerSocioeconomicoDTO request) {
        ParecerSocioeconomico entity = mapper.toEntity(request);
        entity.setStatus("PENDENTE");
        ParecerSocioeconomico salvo = repository.save(entity);
        return mapper.toDTO(salvo);
    }

    @Transactional
    public Optional<ParecerSocioeconomicoDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);

    }

    @Transactional
    public List<ParecerSocioeconomicoDTO> buscarPorUsuarioId(Long userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ParecerSocioeconomicoDTO> atualizar(Long id, ParecerSocioeconomicoDTO request) {
        return repository.findById(id).map(entity -> {
            mapper.updateEntityFromDTO(entity, request);
            entity.setStatus("ATIVO");
            ParecerSocioeconomico atualizado = repository.save(entity);
            return mapper.toDTO(atualizado);
        });
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
