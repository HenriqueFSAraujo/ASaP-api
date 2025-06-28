package pdev.com.agenda.domain.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProcessoDeBolsaDTO {
    private boolean vaiParticipar;
    private boolean jaFoiContemplado;

    @JsonIgnore
    private int percentual;
}
