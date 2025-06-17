package pdev.com.agenda.domain.service;

import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.UserInfoMapper;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoService {

    private final UserInfoRepository repository;
    private final UserInfoMapper mapper;

    public UserInfoService(UserInfoRepository repository, UserInfoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserInfoDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserInfoDTO findById(Long id) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return mapper.toDTO(entity);
    }

    public UserInfoDTO create(UserInfoDTO dto) {

        UserInfo entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public UserInfoDTO update(Long id, UserInfoDTO dto) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        entity.setName(dto.getName());
        entity.setUserName(dto.getUserName());



        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}
