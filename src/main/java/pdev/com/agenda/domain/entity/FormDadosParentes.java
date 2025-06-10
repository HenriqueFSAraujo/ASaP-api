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
import javax.persistence.Table;

@Table(name = "fomulario_dados_parentes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormDadosParentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "parent1Cpf")
    private String parent1Cpf;

    @Column (name = "parent1FullName" )
    private String parent1FullName;

    @Column (name = "parent1Phone" )
    private String parent1Phone;

    @Column (name = "parent1MaritalStatus" )
    private String parent1MaritalStatus;

    @Column(name = "parent2Cpf")
    private String parent2Cpf;

    @Column (name = "parent2FullName" )
    private String parent2FullName;

    @Column (name = "parent2Phone" )
    private String parent2Phone;

    @Column (name = "parent2MaritalStatus" )
    private String parent2MaritalStatus;

    @Column (name = "residesWithBothParents" )
    private String residesWithBothParents;

}

