package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documentos_gerais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosGerais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String singleRegistryRegistration;

    @Column(columnDefinition = "TEXT")
    private String maritalStatus;

    @Column(columnDefinition = "TEXT")
    private String identityDocuments;

    @Column(columnDefinition = "TEXT")
    private String guardianshipDocuments;

    @Column(columnDefinition = "TEXT")
    private String vaccinationCard;

    @Column(columnDefinition = "TEXT")
    private String proofOfResidence;

    @Column(columnDefinition = "TEXT")
    private String workContract;

    @Column(columnDefinition = "TEXT")
    private String bankingRelationsReport;

    @Column(columnDefinition = "TEXT")
    private String proofOfIncome;

    @Column(columnDefinition = "TEXT")
    private String supportingDocumentation;

    @Column(columnDefinition = "TEXT")
    private String bankStatements;

    @Column(columnDefinition = "TEXT")
    private String businessDocuments;

    @Column(columnDefinition = "TEXT")
    private String taxDocuments;

    @Column(columnDefinition = "TEXT")
    private String meiDocuments;

    @Column(columnDefinition = "TEXT")
    private String healthDisability;

    @Column(columnDefinition = "TEXT")
    private String familyComposition;

    @Column(columnDefinition = "TEXT")
    private String governmentProgram;

    @Column(name = "status")
    private String status;
}
