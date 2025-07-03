package pdev.com.agenda.domain.dto;

import lombok.Data;

@Data
public class StatusDTO {

    private boolean active;

    public boolean isActive() {
        return active;
    }
}
