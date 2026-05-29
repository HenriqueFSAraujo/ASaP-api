package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormValidationStatusResponse {

    private Long id;
    private Long userInfoId;
    private FormSectionEnum section;
    private ValidationStatusEnum status;
    private Long reviewerId;
    private LocalDateTime reviewedAt;
    private String comment;
}
