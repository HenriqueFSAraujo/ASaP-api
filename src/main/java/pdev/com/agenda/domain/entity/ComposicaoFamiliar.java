package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "composicao_familiar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComposicaoFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private String escolaridade;
    private String grauParentesco;
    private LocalDate dataNascimento;
    private String profissaoAtiva;
    private String estadoCivil;
    private BigDecimal salarioBruto;
}
