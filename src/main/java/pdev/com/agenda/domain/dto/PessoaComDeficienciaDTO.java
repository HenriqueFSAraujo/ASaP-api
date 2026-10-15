package pdev.com.agenda.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PessoaComDeficienciaDTO(
        Long id,
        String nome,
        String tipoDeficiencia,
        BigDecimal despesaMensal
) {
}
