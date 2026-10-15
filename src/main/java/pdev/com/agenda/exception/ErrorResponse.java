package pdev.com.agenda.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fieldErrors
) {

    @Builder
    public record FieldError(String field, String message) {
    }
}
