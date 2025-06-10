package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;

@Data
@AllArgsConstructor
public class UserLoginResponse {


    private String userName;
    private RoleEnum role;
    private UserInfo userInfo ;

}
