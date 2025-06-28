package pdev.com.agenda.domain.dto;

import lombok.Data;

@Data
public class ProcessoDeBolsaResponse {
    private Long id;
    private boolean vaiParticipar;
    private boolean jaFoiContemplado;
    private int percentual;
}
