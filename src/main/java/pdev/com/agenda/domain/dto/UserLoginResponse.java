package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {


    private String userName;
    private RoleEnum role;
    private UserInfo userInfo ;

}
