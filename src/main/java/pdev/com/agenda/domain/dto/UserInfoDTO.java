package pdev.com.agenda.domain.dto;

import lombok.Data;
import pdev.com.agenda.domain.entity.Role;

@Data
public class UserInfoDTO {
    private Long id;
    private String name;
    private String password;
    private String userName;
    private String token;
    private Role roleName;
    private boolean isFirstLogin;

    public void setIsFirstLogin(boolean firstLogin) {

    }
}
