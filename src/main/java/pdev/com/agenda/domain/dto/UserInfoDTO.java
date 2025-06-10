package pdev.com.agenda.domain.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    private String name;
    private String password;
    private String userName;
    private String token;
    private String roleName;
    private boolean isFirstLogin;

    public void setIsFirstLogin(boolean firstLogin) {

    }
}
