package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "bens_posses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BensPosses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Veiculo> veiculos;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FamiliarEscolaParticular> familiaresEscola;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaComDeficiencia> pessoasComDeficiencia;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DespesaMensal> despesasMensais;

    @Column(name = "status")
    private String status;


}
