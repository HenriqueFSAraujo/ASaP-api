package pdev.com.agenda.domain.dto;

public record ResetPasswordRequest(String currentPassWord, String newPassword) {
}
