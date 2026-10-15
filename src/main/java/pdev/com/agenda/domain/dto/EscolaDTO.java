package pdev.com.agenda.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;

public record EscolaDTO(
        Long id,
        @NotBlank(message = "O nome da escola é obrigatório.") String nome,
        @NotBlank(message = "O CNPJ é obrigatório.")
        @Size(min = 18, message = "CNPJ inválido.") String cnpj,
        String nsu,
        @NotBlank(message = "O endereço da escola é obrigatório.") String endereco,
        @NotNull(message = "O tipo da escola é obrigatório.") TipoEscolaEnum tipo,
        String status
) {
}
