package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Resposta consolidada do GET /api/form-validation/{userId}.
 * Inclui a lista de status por seção + flag "allApproved" para a regra de negócio do modal
 * de "todos os formulários aprovados — parecer pode ser gerado".
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormValidationSummaryResponse {

    private Long userInfoId;
    private List<FormValidationStatusResponse> sections;

    /** True quando todas as 8 seções estão com status APPROVED. */
    private boolean allApproved;
}
