package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@ValidProcessoDeBolsa
@Builder
public record ProcessoDeBolsaDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotNull(message = "Informe se deseja participar do processo.")
        Boolean vaiParticipar,
        @NotNull(message = "Informe se foi contemplado anteriormente.")
        Boolean jaFoiContemplado,
        String percentual,
        @NotNull(message = "O usuário é obrigatório.")
        Long userId,
        @NotBlank(message = "O segmento a cursar é obrigatório.")
        String segmentoAno,
        @NotBlank(message = "A série/ano é obrigatória.")
        String serieAno
) {
}
