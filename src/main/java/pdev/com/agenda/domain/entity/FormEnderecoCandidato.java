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

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    @Column(name = "residencia")
    private String residencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Column(name = "transporte_educacional")
    private String transporteEducacional;

    @Column(name = "tempo_deslocamento")
    private String tempoDeslocamento;

    @Column(name = "atividades_contraturno")
    private String atividadesContraturno;

    @Column(name = "telefone_residencial")
    private String telefoneResidencial;

    @Column(name = "telefone_trabalho")
    private String telefoneTrabalho;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    @Column(name = "email_confirmacao")
    private String emailConfirmacao;

    @Column(name = "responsavel_legal")
    private String responsavelLegal;

    @Column(name = "segmento_2025")
    private String segmento2025;
}
