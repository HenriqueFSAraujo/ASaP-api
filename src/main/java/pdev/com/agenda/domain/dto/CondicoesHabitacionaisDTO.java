package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.CondicoesDeSaudeCasosNaFamilia;
import pdev.com.agenda.domain.enuns.DoencasCronicas;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;

@Builder
public record CondicoesHabitacionaisDTO(
        @NotNull(message = "A situação do imóvel é obrigatória.")
        @JsonProperty("situacaoImovel") SituacaoImovelEnum situacaoImovel,
        @NotNull(message = "O tipo do imóvel é obrigatório.")
        @JsonProperty("tipoImovel") TipoImovelEnum tipoImovel,
        @NotNull(message = "A estrutura física é obrigatória.")
        @JsonProperty("estruturaFisica") EstruturaFisicaEnum estruturaFisica,
        @NotNull(message = "A condição do esgoto sanitário é obrigatória.")
        @JsonProperty("esgotoSanitario") EsgotoSanitarioEnum esgotoSanitario,
        @NotNull(message = "O fornecimento de energia é obrigatório.")
        @JsonProperty("fornecimentoEnergia") FornecimentoEnergiaDTOEnum fornecimentoEnergia,
        @NotNull(message = "O abastecimento de água é obrigatório.")
        @JsonProperty("abastecimentoAgua") AbastecimentoAguaEnum abastecimentoAgua,
        @NotNull(message = "A condição de saúde da família é obrigatória.")
        @JsonProperty("deficienciaFamilia")
        @JsonAlias({"CondicoesDeSaudeCasosNaFamilia", "condicoesDeSaudeCasosNaFamilia"})
        CondicoesDeSaudeCasosNaFamilia CondicoesDeSaudeCasosNaFamilia,
        @NotNull(message = "A informação sobre doenças crônicas é obrigatória.")
        @JsonProperty("doencaCronicaFamilia")
        @JsonAlias("doencasCronicas") DoencasCronicas doencasCronicas,
        @NotNull(message = "O usuário é obrigatório.") Long userId
) {
}
