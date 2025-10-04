package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.CondicoesDeSaudeCasosNaFamilia;
import pdev.com.agenda.domain.enuns.DoencasCronicas;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.FornecimentoEnergiaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "form_condicoes_habitacionais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormCondicoesHabitacionais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SituacaoImovelEnum situacaoImovel;

    @Enumerated(EnumType.STRING)
    private TipoImovelEnum tipoImovel;

    @Enumerated(EnumType.STRING)
    private EstruturaFisicaEnum estruturaFisica;

    @Enumerated(EnumType.STRING)
    private EsgotoSanitarioEnum esgotoSanitario;

    @Enumerated(EnumType.STRING)
    private FornecimentoEnergiaEnum fornecimentoEnergia;

    @Enumerated(EnumType.STRING)
    private AbastecimentoAguaEnum abastecimentoAgua;

    @Enumerated(EnumType.STRING)
    private DoencasCronicas doencasCronicas;

    @Enumerated(EnumType.STRING)
    private CondicoesDeSaudeCasosNaFamilia CondicoesDeSaudeCasosNaFamilia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Column(name = "status")
    private String status;
}
