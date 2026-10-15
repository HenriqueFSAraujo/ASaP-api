package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FormValidationStatusDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long userInfoId,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) FormSectionEnum section,
        @NotNull(message = "O status é obrigatório.") ValidationStatusEnum status,
        Long reviewerId,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) LocalDateTime reviewedAt,
        String comment
) {
}
