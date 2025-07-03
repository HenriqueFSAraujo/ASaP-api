package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.UserInfoResponse;
import pdev.com.agenda.domain.dto.ResetPasswordRequest;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.UserInfoMapper;
import pdev.com.agenda.domain.repository.UserInfoRepository;
import pdev.com.agenda.util.ValidationUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepository repository;
    private final UserInfoMapper mapper;


    public List<UserInfoResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserInfoDTO findById(Long id) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return mapper.toDTO(entity);
    }

    public UserInfoDTO create(UserInfoDTO dto) {
        validateUserInfo(dto);
        UserInfo entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }


    public UserInfoDTO update(Long id, UserInfoDTO dto) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        validateUserInfo(dto);
        entity.setName(dto.getName());
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());

        return mapper.toDTO(repository.save(entity));
    }

    private void validateUserInfo(UserInfoDTO dto) {
        if (!ValidationUtil.isValidEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (!ValidationUtil.isValidCPF(dto.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (repository.existsByUserName(dto.getUserName())) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado.");
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }


    public void resetPassword(Long id, ResetPasswordRequest request) {
        UserInfo user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        if (!user.getPassword().equals(request.getCurrentPassWord())) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }
        user.setFirstLogin(false);
        user.setPassword(request.getNewPassword());
        repository.save(user);
    }

    public void changeUserStatus(Long id, boolean isActive) {
        UserInfo user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        user.setActive(isActive);
        repository.save(user);
    }
}
