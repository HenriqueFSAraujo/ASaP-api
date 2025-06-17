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
        entity.setUserName(dto.getCpf());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setActive(true);
        entity.setFirstLogin(dto.isFirstLogin());
        Role role = roleRepository.findByName(RoleEnum.valueOf(dto.getRoleName()));
        entity.setRole(role);

        return entity;
    }

    public UserInfoDTO toDTO(UserInfo entity) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setName(entity.getName());
        dto.setUserName(entity.getCpf());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setIsFirstLogin(entity.isFirstLogin());
        entity.setActive(true);
        dto.setRoleName(entity.getRole().getName().name());

        return dto;
    }
}