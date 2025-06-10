package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.FornecimentoEnergiaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondicoesHabitacionaisDTO {

    @JsonProperty("situacaoImovel")
    private SituacaoImovelEnum situacaoImovel;

    @JsonProperty("tipoImovel")
    private TipoImovelEnum tipoImovel;

    @JsonProperty("estruturaFisica")
    private EstruturaFisicaEnum estruturaFisica;

    @JsonProperty("esgotoSanitario")
    private EsgotoSanitarioEnum esgotoSanitario;

    @JsonProperty("fornecimentoEnergia")
    private FornecimentoEnergiaEnum fornecimentoEnergia;

    @JsonProperty("abastecimentoAgua")
    private AbastecimentoAguaEnum abastecimentoAgua;
}
