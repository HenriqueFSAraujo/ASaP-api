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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parecer_socioeconomico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParecerSocioeconomico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_aluno")
    private String nomeAluno;

    @Column(name = "data_nascimento_aluno")
    private LocalDate dataNascimentoAluno;

    @Column(name = "segmento_cursar_2025")
    private String segmentoCursar2025;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "cpf_responsavel")
    private String cpfResponsavel;

    @Column(name = "telefone_responsavel")
    private String telefoneResponsavel;

    @Column(name = "renda_bruta_familiar")
    private BigDecimal rendaBrutaFamiliar;

    @Column(name = "quantidade_pessoas_familia")
    private Integer quantidadePessoasFamilia;

    @Column(name = "renda_per_capita")
    private BigDecimal rendaPerCapita;

    @Column(name = "renda_per_capita_salario_minimo")
    private BigDecimal rendaPerCapitaSalarioMinimo;

    @Column(name = "percentual_lc_187")
    private Double percentualLc187;

    @Column(name = "beneficiario_programa_renda")
    private Boolean beneficiarioProgramaRenda;

    @Column(name = "reside_proximo_unidade_escolar")
    private Boolean resideProximoUnidadeEscolar;

    @Column(name = "candidato_com_deficiencia")
    private Boolean candidatoComDeficiencia;

    @Column(name = "doenca_grave_ou_deficiencia_familiar")
    private Boolean doencaGraveOuDeficienciaFamiliar;

    @Column(name = "quantidade_menores_dezoito_anos")
    private Integer quantidadeMenoresDezoitoAnos;

    @Column(name = "aspectos_relevantes", columnDefinition = "TEXT")
    private String aspectosRelevantes;

    @Column(name = "resultado_socioeconomico")
    private String resultadoSocioeconomico;

    @Column(name = "data_finalizacao_parecer")
    private LocalDate dataFinalizacaoParecer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;
}

