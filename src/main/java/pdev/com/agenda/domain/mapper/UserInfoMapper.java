package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.entity.UserInfo;
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
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setToken(dto.getToken());
        entity.setRole(dto.getRoleName());
        entity.setFirstLogin(dto.isFirstLogin());


        return entity;
    }

    public UserInfoDTO toDTO(UserInfo entity) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUserName(entity.getUserName());
        dto.setToken(entity.getToken());
        dto.setIsFirstLogin(entity.isFirstLogin());
        dto.setRoleName(entity.getRole());

        return dto;
    }
}