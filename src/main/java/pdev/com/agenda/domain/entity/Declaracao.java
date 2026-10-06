package pdev.com.agenda.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;
}
