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

    @Column(name = "descricao_atividade")
    private String activityDescription;

    @Column(name = "endereco")
    private String address;

    @Column(name = "atividades_contraturno")
    private String afterSchoolActivities;

    @Column(name = "cidade")
    private String city;

    @Column(name = "tempo_deslocamento")
    private String commutingTime;

    @Column(name = "fonte_energia")
    private String electricitySource;

    @Column(name = "tem_esgoto")
    private String hasSewage;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "ponto_referencia")
    private String referencePoint;

    @Column(name = "residencia")
    private String residenceType;

    @Column(name = "tipo_estrutura")
    private String structureType;

    @Column(name = "tipo_estrutura_outros")
    private String structureTypeOthers;

    @Column(name = "tipo_transporte")
    private String transportType;

    @Column(name = "tipo_transporte_outros")
    private String transportTypeOthers;

    @Column(name = "abastecimento_agua")
    private String waterSupply;

    @Column(name = "frequencia_semanal")
    private String weeklyFrequency;

    @Column(name = "cep")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
}
