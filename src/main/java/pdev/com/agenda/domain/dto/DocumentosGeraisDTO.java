package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentosGeraisDTO(
        @JsonProperty("singleRegistryRegistration") String singleRegistryRegistration,
        @JsonProperty("maritalStatus") String maritalStatus,
        @JsonProperty("identityDocuments") String identityDocuments,
        @JsonProperty("guardianshipDocuments") String guardianshipDocuments,
        @JsonProperty("vaccinationCard") String vaccinationCard,
        @JsonProperty("proofOfResidence") String proofOfResidence,
        @JsonProperty("workContract") String workContract,
        @JsonProperty("bankingRelationsReport") String bankingRelationsReport,
        @JsonProperty("proofOfIncome") String proofOfIncome,
        @JsonProperty("supportingDocumentation") String supportingDocumentation,
        @JsonProperty("bankStatements") String bankStatements,
        @JsonProperty("businessDocuments") String businessDocuments,
        @JsonProperty("taxDocuments") String taxDocuments,
        @JsonProperty("meiDocuments") String meiDocuments,
        @JsonProperty("healthDisability") String healthDisability,
        @JsonProperty("familyComposition") String familyComposition,
        @JsonProperty("governmentProgram") String governmentProgram
) {
}
