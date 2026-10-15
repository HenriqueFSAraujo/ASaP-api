package pdev.com.agenda.domain.dto;

import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;

public record UserLoginResponse(String userName, RoleEnum role, UserInfo userInfo) {
}
