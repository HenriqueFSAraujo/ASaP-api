package pdev.com.agenda.domain.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private boolean vaiParticipar;
    private boolean jaFoiContemplado;
    private int percentual;

}
