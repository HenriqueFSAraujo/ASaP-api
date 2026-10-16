package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FornecimentoEnergiaDTOEnum {
    COMPANHIA_EXISTENTE,
    INEXISTENTE,
    OUTRO,
    GERADOR,
    EXISTENTE;

    @JsonCreator
    public static FornecimentoEnergiaDTOEnum fromJson(String value) {
        if ("COMPANHIA_ELETRICA".equals(value)) {
            return COMPANHIA_EXISTENTE;
        }
        return value == null ? null : valueOf(value);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
