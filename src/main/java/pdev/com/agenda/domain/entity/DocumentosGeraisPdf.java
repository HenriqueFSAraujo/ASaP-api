package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_gerais_pdf")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosGeraisPdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;


    private byte[] singleRegistryRegistration;


    private byte[] maritalStatus;


    private byte[] identityDocuments;


    private byte[] guardianshipDocuments;


    private byte[] vaccinationCard;

    private byte[] proofOfResidence;


    private byte[] workContract;

    private byte[] bankingRelationsReport;


    private byte[] proofOfIncome;

    private byte[] supportingDocumentation;


    private byte[] bankStatements;


    private byte[] businessDocuments;


    private byte[] taxDocuments;


    private byte[] meiDocuments;


    private byte[] healthDisability;


    private byte[] familyComposition;


    private byte[] governmentProgram;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
