package pdev.com.agenda.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserInfoResponse{

    private Long userId;
    private String name;
    private String userName;
    private String roleName;
    private String cpf;
    private String email;
    private boolean isFirstLogin;
    private String password;
    private boolean isActive;

    public void setIsFirstLogin(boolean firstLogin) {

    }
}