package pdev.com.agenda.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormEnderecoCandidatoDTO {

    private Long id;

    @JsonProperty("zipCode")
    private String cep;

    @JsonProperty("address")
    private String endereco;

    @JsonProperty("neighborhood")
    private String bairro;

    @JsonProperty("city")
    private String cidade;

    @JsonProperty("referencePoint")
    private String pontoReferencia;

    @JsonProperty("residenceType")
    private String residencia;

    private Long userId;


//
//    @JsonProperty("transportUsage")
//    private String transporteEducacional;
//
//    @JsonProperty("travelTime")
//    private String tempoDeslocamento;
//
//    @JsonProperty("extracurricularActivities")
//    private String atividadesContraturno;
//
//    @JsonProperty("homePhone")
//    private String telefoneResidencial;
//
//    @JsonProperty("workPhone")
//    private String telefoneTrabalho;
//
//    @JsonProperty("mobilePhone")
//    private String telefoneCelular;
//
//    @JsonProperty("email")
//    private String emailConfirmacao;
//
//    @JsonProperty("legalGuardian")
//    private String responsavelLegal;
//
//    @JsonProperty("studySegment")
//    private String segmento2025;
}

