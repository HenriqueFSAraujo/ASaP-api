package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosGeraisDTO {
    @JsonProperty("singleRegistryRegistration")
    private String singleRegistryRegistration;

    @JsonProperty("maritalStatus")
    private String maritalStatus;

    @JsonProperty("identityDocuments")
    private String identityDocuments;

    @JsonProperty("guardianshipDocuments")
    private String guardianshipDocuments;

    @JsonProperty("vaccinationCard")
    private String vaccinationCard;

    @JsonProperty("proofOfResidence")
    private String proofOfResidence;

    @JsonProperty("workContract")
    private String workContract;

    @JsonProperty("bankingRelationsReport")
    private String bankingRelationsReport;

    @JsonProperty("proofOfIncome")
    private String proofOfIncome;

    @JsonProperty("supportingDocumentation")
    private String supportingDocumentation;

    @JsonProperty("bankStatements")
    private String bankStatements;

    @JsonProperty("businessDocuments")
    private String businessDocuments;

    @JsonProperty("taxDocuments")
    private String taxDocuments;

    @JsonProperty("meiDocuments")
    private String meiDocuments;

    @JsonProperty("healthDisability")
    private String healthDisability;

    @JsonProperty("familyComposition")
    private String familyComposition;

    @JsonProperty("governmentProgram")
    private String governmentProgram;
}
