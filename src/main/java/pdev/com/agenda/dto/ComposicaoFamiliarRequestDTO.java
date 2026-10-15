package pdev.com.agenda.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ComposicaoFamiliarRequestDTO(
        @NotNull(message = "O usuário é obrigatório.") Long userInfoId,
        List<@Valid ComposicaoFamiliarDTO> composicaoFamiliar
) {
}
