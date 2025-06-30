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
public class FormDadosParentesDTO {

     private Long id;

    @JsonProperty("parent1Cpf")
    private String parent1Cpf;

    @JsonProperty ( "parent1FullName" )
    private String parent1FullName;

    @JsonProperty("parent1Phone" )
    private String parent1Phone;

    @JsonProperty ( "parent1MaritalStatus" )
    private String parent1MaritalStatus;

    @JsonProperty( "parent2Cpf")
    private String parent2Cpf;

    @JsonProperty ( "parent2FullName" )
    private String parent2FullName;

    @JsonProperty ( "parent2Phone" )
    private String parent2Phone;

    @JsonProperty ( "parent2MaritalStatus" )
    private String parent2MaritalStatus;

    @JsonProperty ("residesWithBothParents" )
    private String residesWithBothParents;

    private Long userId;
}
