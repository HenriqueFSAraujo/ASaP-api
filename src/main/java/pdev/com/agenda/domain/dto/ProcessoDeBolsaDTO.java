package pdev.com.agenda.domain.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDeBolsaDTO {
    private boolean vaiParticipar;
    private boolean jaFoiContemplado;

    @JsonIgnore
    private BigDecimal percentual;
}
