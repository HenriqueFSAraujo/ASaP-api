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

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String marcaModelo;


    @Column(nullable = true)
      private Integer anoFabricacao;

    @Column(nullable = true)
    private String utilizacao;

    @ManyToOne
    @JoinColumn(name = "bens_posses_id", nullable = true)
    private BensPosses bensPosses;

    @Column(name = "status")
    private String status;
}