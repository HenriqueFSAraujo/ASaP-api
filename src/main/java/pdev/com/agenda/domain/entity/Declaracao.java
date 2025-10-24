package pdev.com.agenda.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "declaracoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDeclarante;
    private String rgDeclarante;
    private String cpfDeclarante;
    private String nomeAluno;

    private Boolean aceiteTermos;
}
