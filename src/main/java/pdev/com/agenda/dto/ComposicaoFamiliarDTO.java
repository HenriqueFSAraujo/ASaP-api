package pdev.com.agenda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComposicaoFamiliarDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private String parentesco;

    private Long userId;
}
