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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
