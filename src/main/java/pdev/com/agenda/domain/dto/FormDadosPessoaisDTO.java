package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDadosPessoaisDTO {

    private Long id;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("nacionalidade")
    private String nacionalidade;

    @JsonProperty("naturalidade")
    private String naturalidade;

    @JsonProperty("cor")
    private String cor;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("cpfScholarship")
    private String cpfBolsista;

    @JsonProperty("dateBirth")
    private LocalDate dataNascimento;

    @JsonProperty("deficiency")
    private String pcd;

    @JsonProperty("educasenso")
    private String numEducasenso;

    @JsonProperty("status")
    private String status;

    private Long userId;

}