package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "bens_posse")
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
    @Column(nullable = true)
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<FamiliarEscolaParticular> familiaresEscola;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<PessoaComDeficiencia> pessoasComDeficiencia;

    @OneToMany(mappedBy = "bensPosses", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<DespesaMensal> despesasMensais;
}
