package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidFormEnderecoCandidato
public record FormEnderecoCandidatoDTO(
        @JsonProperty("activityDescription") String activityDescription,
        @NotBlank(message = "Endereço é obrigatório.")
        @JsonProperty("address") String address,
        @NotBlank(message = "Informe se participa de atividades após a escola.")
        @JsonProperty("afterSchoolActivities") String afterSchoolActivities,
        @NotBlank(message = "Cidade é obrigatória.")
        @JsonProperty("city") String city,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("commutingTime") String commutingTime,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("electricitySource") String electricitySource,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("hasSewage") String hasSewage,
        @NotBlank(message = "Bairro é obrigatório.")
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("referencePoint") String referencePoint,
        @NotBlank(message = "Informe onde o candidato reside.")
        @JsonProperty("residenceType") String residenceType,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("structureType") String structureType,
        @JsonProperty("structureTypeOthers") String structureTypeOthers,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("transportType") String transportType,
        @JsonProperty("transportTypeOthers") String transportTypeOthers,
        @NotBlank(message = "Campo obrigatório.")
        @JsonProperty("waterSupply") String waterSupply,
        @JsonProperty("weeklyFrequency") String weeklyFrequency,
        @NotBlank(message = "CEP é obrigatório.")
        @JsonProperty("zipCode") String zipCode,
        @NotNull(message = "O usuário é obrigatório.")
        @JsonProperty("userId") Long userId
) {
}
