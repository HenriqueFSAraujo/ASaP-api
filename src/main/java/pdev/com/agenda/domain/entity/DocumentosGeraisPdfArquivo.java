package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_gerais_pdf_arquivos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosGeraisPdfArquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    @Column(name = "tipo_documento", nullable = false, length = 80)
    private String tipoDocumento;

    @Column(name = "nome_arquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    @Column(name = "status", nullable = false)
    private String status;
}
