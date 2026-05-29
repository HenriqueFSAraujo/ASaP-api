package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;

import javax.validation.constraints.NotNull;

/**
 * Payload para atualização de status de uma seção. O {@code reviewerId} pode vir do JWT
 * no futuro; por ora é enviado pelo front (admin logado) e validado no service.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormValidationStatusRequest {

    @NotNull(message = "O status é obrigatório.")
    private ValidationStatusEnum status;

    /**
     * ID do admin que está revisando. Opcional — se ausente, o registro fica sem reviewer
     * (mas o reviewed_at é sempre preenchido).
     */
    private Long reviewerId;

    /** Comentário opcional (ex: motivo da rejeição). */
    private String comment;
}
