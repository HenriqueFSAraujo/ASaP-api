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

@Entity
@Table(name = "pessoa_com_deficiencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaComDeficiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String nome;

    @Column(nullable = true)
    private String tipoDeficiencia;

    @Column(nullable = true)
    private BigDecimal despesaMensal;

    @ManyToOne
    @JoinColumn(name = "bens_posses_id", nullable = true)
    private BensPosses bensPosses;
}
