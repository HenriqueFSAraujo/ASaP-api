package pdev.com.agenda.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco_candidatos")
@Getter
@Setter
public class FormEnderecoCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activitydescription")
    private String activityDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "afterschoolactivities")
    private String afterSchoolActivities;

    @Column(name = "city")
    private String city;

    @Column(name = "commutingtime")
    private String commutingTime;

    @Column(name = "electricitysource")
    private String electricitySource;

    @Column(name = "hasSewage")
    private String hasSewage;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "referencepoint")
    private String referencePoint;

    @Column(name = "residencetype")
    private String residenceType;

    @Column(name = "structuretype")
    private String structureType;

    @Column(name = "structuretypeothers")
    private String structureTypeOthers;

    @Column(name = "transporttype")
    private String transportType;

    @Column(name = "transporttpeothers")
    private String transportTypeOthers;

    @Column(name = "waterSupply")
    private String waterSupply;

    @Column(name = "weeklyfrequency")
    private String weeklyFrequency;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
}
