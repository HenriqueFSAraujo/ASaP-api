package pdev.com.agenda.domain;

import lombok.Data;
import pdev.com.agenda.domain.enuns.TipoAlunoEnum;

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
    private TipoAlunoEnum tipoAluno;

    public void setIsFirstLogin(boolean firstLogin) {
        this.isFirstLogin = firstLogin;
    }
}