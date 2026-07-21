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

/**
 * Um arquivo PDF individual enviado por um usuario para um tipo de documento.
 * Substitui o modelo antigo (DocumentosGeraisPdf: 1 linha por usuario, 17 colunas
 * BYTEA, 1 arquivo por tipo) por uma relacao real 1 usuario : N documentos por tipo.
 */
@Entity
@Table(name = "documento_pdf")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    @Column(name = "status")
    private String status;
}
