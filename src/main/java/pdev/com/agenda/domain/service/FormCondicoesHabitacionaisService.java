package pdev.com.agenda.domain.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.FormCondicoesHabitacionaisMapper;
import pdev.com.agenda.domain.repository.FormCondicoesHabitacionaisRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormCondicoesHabitacionaisService {

    private final FormCondicoesHabitacionaisRepository repository;
    private final FormCondicoesHabitacionaisMapper mapper;


    @Transactional
    public CondicoesHabitacionaisDTO salvarOuAtualizar(CondicoesHabitacionaisDTO dto) {
        UserInfo user = new UserInfo();
        user.setId(dto.userId());

        Optional<FormCondicoesHabitacionais> existente = repository.findByUser(user);

        FormCondicoesHabitacionais entidade;
        if (existente.isPresent()) {
            entidade = existente.get();
            mapper.updateEntityFromDTO(dto, entidade);
        } else {
            entidade = mapper.toEntity(dto);
            entidade.setUser(user);
            entidade.setStatus("ATIVO");
        }

        FormCondicoesHabitacionais salvo = repository.save(entidade);
        return mapper.toDTO(salvo);
    }

    @Transactional
    public Optional<CondicoesHabitacionaisDTO> getByUserId(Long userId) {
        return repository.findByUser_Id(userId)
                .map(mapper::toDTO)
                .or(() -> {
                    throw new EntityNotFoundException(
                            "Condições habitacionais não encontradas para o usuário: " + userId
                    );
                });
    }
    public List<FormCondicoesHabitacionais> getAll() {
        return repository.findAll();
    }

    public FormCondicoesHabitacionais update(Long id, CondicoesHabitacionaisDTO dto) {
        Optional<FormCondicoesHabitacionais> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            FormCondicoesHabitacionais entity = optionalEntity.get();
            mapper.updateEntityFromDTO(dto, entity);
            entity.setStatus("PENDENTE");
            return repository.save(entity);
        }
        return null;
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

}
