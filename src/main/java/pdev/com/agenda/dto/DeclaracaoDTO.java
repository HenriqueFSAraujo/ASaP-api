package pdev.com.agenda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeclaracaoDTO {

    private Long id;
    private String nomeDeclarante;
    private String rgDeclarante;
    private String cpfDeclarante;
    private String nomeAluno;
    private Boolean aceiteTermos;
}
