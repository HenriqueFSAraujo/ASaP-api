package pdev.com.agenda.domain.dto;

import lombok.Builder;

import java.util.List;

/**
 * Resposta consolidada do GET /api/form-validation/{userId}.
 */
@Builder
public record FormValidationSummaryResponse(
        Long userInfoId,
        List<FormValidationStatusDTO> sections,
        boolean allApproved
) {
}
