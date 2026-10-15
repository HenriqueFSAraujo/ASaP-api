package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.util.FlexibleLocalDateDeserializer;

import java.time.LocalDate;

public record FormDadosPessoaisDTO(
        Long id,
        @NotBlank(message = "O nome completo é obrigatório.")
        @JsonProperty("fullName") String fullName,
        @Email(message = "E-mail inválido.")
        @JsonProperty("email") String email,
        @NotBlank(message = "O CPF é obrigatório.")
        @JsonProperty("cpf") String cpf,
        @JsonProperty("rg") String rg,
        @NotBlank(message = "A nacionalidade é obrigatória.")
        @JsonProperty("nacionalidade") String nacionalidade,
        @NotBlank(message = "A naturalidade é obrigatória.")
        @JsonProperty("naturalidade") String naturalidade,
        @NotBlank(message = "Raça/cor é obrigatória.")
        @JsonProperty("cor") String cor,
        @NotBlank(message = "O celular é obrigatório.")
        @JsonProperty("phone") String phone,
        @NotBlank(message = "O gênero é obrigatório.")
        @JsonProperty("gender") String gender,
        @JsonProperty("cpfScholarship") String cpfBolsista,
        @JsonProperty("dateBirth")
        @JsonDeserialize(using = FlexibleLocalDateDeserializer.class) LocalDate dataNascimento,
        @NotBlank(message = "A informação sobre deficiência é obrigatória.")
        @JsonProperty("deficiency") String pcd,
        @JsonProperty("educasenso") String numEducasenso,
        @JsonProperty("status") String status,
        @NotNull(message = "O usuário é obrigatório.")
        Long userId,
        @NotNull(message = "A escola é obrigatória.")
        @JsonProperty("escolaId")
        Long escolaId,
        @NotNull(message = "O tipo da escola é obrigatório.")
        @JsonProperty("tipoEscola") TipoEscolaEnum tipoEscola
) {
}
