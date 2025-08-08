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

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("cpfScholarship")

    private String cpfBolsista;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dateBirth")
    private LocalDate dataNascimento;

    @JsonProperty("deficiency")
    private String pcd;

    @JsonProperty("educasenso")
    private String numEducasenso;

    private Long userId;

}