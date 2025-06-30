package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDeBolsaResponse {
    private Long id;
    private boolean vaiParticipar;
    private boolean jaFoiContemplado;
    private String percentual;
    private Long userId;
}
