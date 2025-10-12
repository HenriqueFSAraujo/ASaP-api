package pdev.com.agenda.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "processo_de_bolsa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDeBolsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vai_participar")
    private boolean vaiParticipar;

    @Column(name = "ja_foi_contemplado")
    private boolean jaFoiContemplado;

    @Column(name = "percentual")
    private String percentual;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @Column(name = "status")
    private String status;

    @Column(name = "segmento_ano")
    private String segmentoAno;

    @Column(name = "serie_ano")
    private String serieAno;
}
