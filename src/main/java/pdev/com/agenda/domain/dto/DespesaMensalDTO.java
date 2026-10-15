package pdev.com.agenda.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DespesaMensalDTO(
        Long id,
        @NotBlank(message = "A descrição da despesa é obrigatória.") String descricao,
        @NotNull(message = "O valor da despesa é obrigatório.") BigDecimal valor
) {
}
