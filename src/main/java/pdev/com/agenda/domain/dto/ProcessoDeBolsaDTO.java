package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDeBolsaDTO {
    private boolean vaiParticipar;
    private boolean jaFoiContemplado;
    private Long userId;
    private String percentual;
}
