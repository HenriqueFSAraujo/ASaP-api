package pdev.com.agenda.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pdev.com.agenda.util.FlexibleLocalDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComposicaoFamiliarDTO(
        Long id,
        String nome,
        Integer idade,
        String parentesco,
        String nomeCompleto,
        String escolaridade,
        String grauParentesco,
        @JsonDeserialize(using = FlexibleLocalDateDeserializer.class) LocalDate dataNascimento,
        String profissaoAtiva,
        String estadoCivil,
        BigDecimal salarioBruto
) {
}
