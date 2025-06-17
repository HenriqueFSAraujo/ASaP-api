package pdev.com.agenda.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ROLE_USER("ROLE_USER", "ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN", "ROLE_ADMIN");

    private final String code;
    private final String description;

}
