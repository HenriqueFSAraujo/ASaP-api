package pdev.com.agenda.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import pdev.com.agenda.util.FlexibleLocalDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ParecerSocioeconomicoDTO(
        @NotNull(message = "O usuário é obrigatório.")
        Long userId,
        @NotBlank(message = "O nome do aluno é obrigatório.")
        String nomeAluno,
        @JsonDeserialize(using = FlexibleLocalDateDeserializer.class) LocalDate dataNascimentoAluno,
        @NotBlank(message = "O segmento a cursar é obrigatório.")
        String segmentoCursar2025,
        @NotBlank(message = "O nome do responsável é obrigatório.")
        String nomeResponsavel,
        @NotBlank(message = "O CPF do responsável é obrigatório.")
        String cpfResponsavel,
        String telefoneResponsavel,
        @NotNull(message = "A renda bruta familiar é obrigatória.")
        @Min(value = 1, message = "A renda bruta familiar deve ser maior que zero.")
        BigDecimal rendaBrutaFamiliar,
        @NotNull(message = "O total de componentes familiares é obrigatório.")
        @Min(value = 1, message = "O total de componentes deve ser maior que zero.")
        Integer totalComponentesFamilar,
        @NotNull(message = "A renda per capita é obrigatória.")
        @Min(value = 0, message = "A renda per capita não pode ser negativa.")
        BigDecimal rendaPerCapita,
        @NotNull(message = "A renda per capita em salários mínimos é obrigatória.")
        @Min(value = 0, message = "A renda per capita em salários mínimos não pode ser negativa.")
        BigDecimal rendaPerCapitaSalarioMinimo,
        @NotBlank(message = "O percentual conforme a LC 187 é obrigatório.")
        @Pattern(regexp = "100%|Indeferido", message = "Percentual inválido.")
        String percentualLc187,
        @NotNull(message = "Informe se é beneficiário de programa de transferência de renda.")
        Boolean beneficiarioProgramaRenda,
        @NotNull(message = "Informe se reside próximo à unidade escolar.")
        Boolean resideProximoUnidadeEscolar,
        @NotNull(message = "Informe se o candidato possui deficiência.")
        Boolean candidatoComDeficiencia,
        @NotNull(message = "Informe se há doença grave ou deficiência na família.")
        Boolean doencaGraveOuDeficienciaFamiliar,
        @NotNull(message = "A quantidade de menores de 18 anos é obrigatória.")
        @Min(value = 0, message = "A quantidade de menores não pode ser negativa.")
        Integer quantidadeMenoresDezoitoAnos,
        String aspectosRelevantes,
        @Pattern(regexp = "Deferido|Indeferido", message = "Resultado socioeconômico inválido.")
        String resultadoSocioeconomico,
        @JsonDeserialize(using = FlexibleLocalDateDeserializer.class) LocalDate dataFinalizacaoParecer
) {
}
