package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscolaDTO {

    private Long id;

    @NotBlank(message = "O nome da escola é obrigatório.")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    private String cnpj;

    private String nsu;

    private String endereco;

    @NotNull(message = "O tipo da escola é obrigatório.")
    private TipoEscolaEnum tipo;

    private String status;
}
