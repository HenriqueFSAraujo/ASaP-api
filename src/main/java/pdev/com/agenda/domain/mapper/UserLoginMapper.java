package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.UserLoginRequest;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;

@Component
public class UserLoginMapper {

    public UserInfo toEntity(UserLoginRequest dto) {
        if (dto == null) {
            return null;
        }

        UserInfo userLogin = new UserInfo();
        userLogin.setUserName(dto.getUserName());
        userLogin.setPassword(dto.getPassword());
        return userLogin;
    }

    public UserLoginResponse toDto(UserInfo entity) {
        if (entity == null) {
            return null;
        }

        RoleEnum roleName = (entity.getRole() != null)
                ? entity.getRole().getName()
                : null;

        return new UserLoginResponse(
                entity.getUserName(),
                roleName,
                entity
        );
    }
}
