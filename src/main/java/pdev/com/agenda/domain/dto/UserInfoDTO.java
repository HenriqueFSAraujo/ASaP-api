package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import pdev.com.agenda.domain.enuns.TipoAlunoEnum;

@Builder
public record UserInfoDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long userId,
        @NotBlank(message = "O nome é obrigatório.") String name,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) String userName,
        @NotBlank(message = "O perfil (roleName) é obrigatório.")
        @Pattern(regexp = "ROLE_USER|ROLE_ADMIN", message = "Perfil inválido. Valores aceitos: ROLE_USER, ROLE_ADMIN.")
        String roleName,
        @NotBlank(message = "O CPF é obrigatório.") String cpf,
        @Email(message = "E-mail inválido.") String email,
        @JsonProperty("isFirstLogin") boolean isFirstLogin,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) String password,
        @JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY) boolean isActive,
        TipoAlunoEnum tipoAluno
) {
    public UserInfoDTO withEmail(String newEmail) {
        return new UserInfoDTO(userId, name, userName, roleName, cpf, newEmail, isFirstLogin, password, isActive, tipoAluno);
    }

    public UserInfoDTO withTipoAluno(TipoAlunoEnum newTipoAluno) {
        return new UserInfoDTO(userId, name, userName, roleName, cpf, email, isFirstLogin, password, isActive, newTipoAluno);
    }
}
