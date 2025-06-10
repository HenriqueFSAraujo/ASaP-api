package pdev.com.agenda.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoImovelEnum {

    PROPRIO,
    FINANCIADO,
    CEDIDO,
    ALUGADO,
    COMPARTILHADO_COM_OUTRA_FAMILIA
}
