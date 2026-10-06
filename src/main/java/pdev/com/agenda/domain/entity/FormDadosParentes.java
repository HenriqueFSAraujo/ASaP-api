package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
}
