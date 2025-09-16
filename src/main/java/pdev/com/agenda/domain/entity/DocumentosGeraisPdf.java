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
    private Usuario usuario;

    @Lob
    private byte[] singleRegistryRegistration;
    @Lob
    private byte[] maritalStatus;
    @Lob
    private byte[] identityDocuments;
    @Lob
    private byte[] guardianshipDocuments;
    @Lob
    private byte[] vaccinationCard;
    @Lob
    private byte[] proofOfResidence;
    @Lob
    private byte[] workContract;
    @Lob
    private byte[] bankingRelationsReport;
    @Lob
    private byte[] proofOfIncome;
    @Lob
    private byte[] supportingDocumentation;
    @Lob
    private byte[] bankStatements;
    @Lob
    private byte[] businessDocuments;
    @Lob
    private byte[] taxDocuments;
    @Lob
    private byte[] meiDocuments;
    @Lob
    private byte[] healthDisability;
    @Lob
    private byte[] familyComposition;
    @Lob
    private byte[] governmentProgram;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;
}

