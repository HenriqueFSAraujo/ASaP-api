package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    @JsonIgnore
    private Long userId;
    private String name;
    @JsonIgnore
    private String userName;
    private String roleName;
    private String cpf;
    private String email;
    private boolean isFirstLogin;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean isActive;


    public void setIsFirstLogin(boolean firstLogin) {

    }
}
