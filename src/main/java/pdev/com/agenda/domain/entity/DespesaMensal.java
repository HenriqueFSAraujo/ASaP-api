package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "despesa_mensal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespesaMensal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "bens_posses_id", nullable = true)
    private BensPosses bensPosses;

    @Column(name = "status")
    private String status;
}