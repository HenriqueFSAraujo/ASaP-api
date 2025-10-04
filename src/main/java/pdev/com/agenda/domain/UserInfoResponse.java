package pdev.com.agenda.domain;

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


}