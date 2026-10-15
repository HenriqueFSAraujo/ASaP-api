package pdev.com.agenda.domain.dto;

import jakarta.validation.constraints.NotNull;

public record StatusDTO(@NotNull(message = "O status ativo é obrigatório.") Boolean active) {
}
