package pdev.com.agenda.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComposicaoFamiliarDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private String parentesco;
    private String nomeCompleto;
    private String escolaridade;
    private String grauParentesco;
    private LocalDate dataNascimento;
    private String profissaoAtiva;
    private String estadoCivil;
    private BigDecimal salarioBruto;

    private Long userId;
}
