package pdev.com.agenda.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Resposta padronizada de erro, devolvida pelo {@link GlobalExceptionHandler}.
 * <p>
 * Estrutura:
 * <pre>
 * {
 *   "timestamp": "2026-05-28T10:15:30.123-03:00",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "Tipo de aluno é obrigatório para usuários com perfil ALUNO.",
 *   "path": "/api/users",
 *   "fieldErrors": [ { "field": "email", "message": "E-mail inválido" } ]   // opcional
 * }
 * </pre>
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final OffsetDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<FieldError> fieldErrors;

    @Getter
    @Builder
    public static class FieldError {
        private final String field;
        private final String message;
    }
}
