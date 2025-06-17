package pdev.com.agenda.domain.dto;

import lombok.Data;
import pdev.com.agenda.domain.entity.Role;

@Data
public class UserInfoDTO {

    private String name;
    private String userName;
    private String roleName;
    private boolean isFirstLogin;

    public void setIsFirstLogin(boolean firstLogin) {

    }
}
