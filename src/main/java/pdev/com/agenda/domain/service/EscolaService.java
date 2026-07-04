package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.dto.EscolaDTO;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.domain.mapper.EscolaMapper;
import pdev.com.agenda.domain.repository.EscolaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository escolaRepository;
    private final EscolaMapper escolaMapper;

    @Transactional(readOnly = true)
    public List<EscolaDTO> findAll() {
        return escolaRepository.findAll().stream()
                .map(escolaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EscolaDTO findById(Long id) {
        return escolaMapper.toDTO(getEscolaOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<EscolaDTO> findByTipo(TipoEscolaEnum tipo) {
        return escolaRepository.findByTipo(tipo).stream()
                .map(escolaMapper::toDTO)
                .toList();
    }

    public EscolaDTO create(EscolaDTO dto) {
        validateForCreate(dto);
        Escola entity = escolaMapper.toEntity(dto);
        entity.setId(null);
        return escolaMapper.toDTO(escolaRepository.save(entity));
    }

    public EscolaDTO update(Long id, EscolaDTO dto) {
        Escola entity = getEscolaOrThrow(id);
        validateForUpdate(id, dto);
        escolaMapper.updateEntityFromDto(dto, entity);
        return escolaMapper.toDTO(escolaRepository.save(entity));
    }

    public void delete(Long id) {
        Escola entity = getEscolaOrThrow(id);
        escolaRepository.delete(entity);
    }

    private Escola getEscolaOrThrow(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com ID: " + id));
    }

    private void validateForCreate(EscolaDTO dto) {
        if (escolaRepository.existsByCnpj(dto.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado.");
        }
    }

    private void validateForUpdate(Long id, EscolaDTO dto) {
        if (escolaRepository.existsByCnpjAndIdNot(dto.getCnpj(), id)) {
            throw new IllegalArgumentException("CNPJ já cadastrado para outra escola.");
        }
    }
}
