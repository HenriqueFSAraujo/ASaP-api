package pdev.com.agenda.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeclaracaoDTO(
        Long id,
        @NotBlank(message = "O nome do declarante é obrigatório.") String nomeDeclarante,
        @NotBlank(message = "O RG do declarante é obrigatório.") String rgDeclarante,
        @NotBlank(message = "O CPF do declarante é obrigatório.") String cpfDeclarante,
        @NotBlank(message = "O nome do aluno é obrigatório.") String nomeAluno,
        @NotNull(message = "É necessário aceitar os termos.")
        @AssertTrue(message = "É necessário aceitar os termos.") Boolean aceiteTermos,
        @NotNull(message = "O usuário é obrigatório.") Long userId
) {
}
