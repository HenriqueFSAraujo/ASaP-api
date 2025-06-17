package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.entity.Role;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;
import pdev.com.agenda.domain.repository.RoleRepository;



@Component
public class UserInfoMapper {

    private final RoleRepository roleRepository;

    public UserInfoMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserInfo toEntity(UserInfoDTO dto) {
        UserInfo entity = new UserInfo();
        entity.setName(dto.getName());
        entity.setId(dto.getUserId());
        entity.setUserName(dto.getCpf());
        entity.setCpf(dto.getCpf());
        entity.setPassword(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setActive(true);
        entity.setFirstLogin(dto.isFirstLogin());
        if (dto.getRoleName() == null) {
            throw new IllegalArgumentException("O campo role é obrigatório.");
        }
        Role role = roleRepository.findByName(RoleEnum.valueOf(dto.getRoleName()));
        entity.setRole(role);

        return entity;
    }

    public UserInfoDTO toDTO(UserInfo entity) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUserId(entity.getId());
        dto.setName(entity.getName());
        dto.setUserName(entity.getCpf());
        dto.setCpf(entity.getCpf());
        dto.setPassword(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setIsFirstLogin(entity.isFirstLogin());
        dto.setActive(true);
        dto.setRoleName(entity.getRole().getName().name());

        return dto;
    }
}