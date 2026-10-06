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
