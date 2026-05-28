package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdev.com.agenda.domain.enuns.TipoAlunoEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    @JsonIgnore
    private Long userId;

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @JsonIgnore
    private String userName;

    @NotBlank(message = "O perfil (roleName) é obrigatório.")
    @Pattern(regexp = "ROLE_USER|ROLE_ADMIN", message = "Perfil inválido. Valores aceitos: ROLE_USER, ROLE_ADMIN.")
    private String roleName;

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    private boolean isFirstLogin;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean isActive;

    /**
     * Classificacao do aluno (ESCOLA_PARTICULAR ou ESCOLA_GRATUITA).
     * Obrigatorio quando roleName = ROLE_USER; deve ser nulo quando roleName = ROLE_ADMIN.
     * Validacao aplicada em UserInfoService.validateTipoAluno (regra cross-field).
     */
    private TipoAlunoEnum tipoAluno;


    public void setIsFirstLogin(boolean firstLogin) {

    }
}
