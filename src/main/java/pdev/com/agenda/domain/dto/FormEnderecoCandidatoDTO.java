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
public class FormEnderecoCandidatoDTO {


    @JsonProperty("activityDescription")
    private String activityDescription;

    @JsonProperty("address")
    private String address;

    @JsonProperty("afterSchoolActivities")
    private String afterSchoolActivities;

    @JsonProperty("city")
    private String city;

    @JsonProperty("commutingTime")
    private String commutingTime;

    @JsonProperty("electricitySource")
    private String electricitySource;

    @JsonProperty("hasSewage")
    private String hasSewage;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("referencePoint")
    private String referencePoint;

    @JsonProperty("residenceType")
    private String residenceType;

    @JsonProperty("structureType")
    private String structureType;

    @JsonProperty("structureTypeOthers")
    private String structureTypeOthers;

    @JsonProperty("transportType")
    private String transportType;

    @JsonProperty("transportTypeOthers")
    private String transportTypeOthers;

    @JsonProperty("waterSupply")
    private String waterSupply;

    @JsonProperty("weeklyFrequency")
    private String weeklyFrequency;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("userId")
    private Long userId;
}
