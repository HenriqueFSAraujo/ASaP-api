package pdev.com.agenda.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordRequest {
    private String currentPassWord;
    private String newPassword;
}
