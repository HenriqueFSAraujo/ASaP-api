package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FormDadosParentesDTO(
        Long id,
        @JsonProperty("parent1Cpf") String parent1Cpf,
        @JsonProperty("parent1FullName") String parent1FullName,
        @JsonProperty("parent1Phone") String parent1Phone,
        @JsonProperty("parent1MaritalStatus") String parent1MaritalStatus,
        @JsonProperty("parent2Cpf") String parent2Cpf,
        @JsonProperty("parent2FullName") String parent2FullName,
        @JsonProperty("parent2Phone") String parent2Phone,
        @JsonProperty("parent2MaritalStatus") String parent2MaritalStatus,
        @NotBlank(message = "É obrigatório informar se o candidato reside com os dois genitores.")
        @JsonProperty("residesWithBothParents") String residesWithBothParents,
        @NotNull(message = "O usuário é obrigatório.") Long userId
) {
}
