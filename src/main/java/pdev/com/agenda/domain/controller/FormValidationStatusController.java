package pdev.com.agenda.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.FormValidationStatusRequest;
import pdev.com.agenda.domain.dto.FormValidationStatusResponse;
import pdev.com.agenda.domain.dto.FormValidationSummaryResponse;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.service.FormValidationStatusService;

import javax.validation.Valid;

/**
 * Endpoints para gerenciar o status de validação do admin por seção do formulário.
 * <p>
 * Regra de negócio: apenas usuários com role {@code ROLE_ADMIN} podem alterar o status — a
 * validação é feita no service via o campo {@code reviewerId} do payload. Futuramente, com
 * Spring Security habilitado, será trocada por {@code @PreAuthorize("hasRole('ADMIN')")}.
 */
@RestController
@RequestMapping("/api/form-validation")
@RequiredArgsConstructor
public class FormValidationStatusController {

    private final FormValidationStatusService service;

    /**
     * Retorna o resumo de validação do aluno: lista de 8 seções (cada uma com status,
     * reviewer, reviewedAt, comment) + flag {@code allApproved} para a regra do modal.
     */
    @GetMapping("/{userInfoId}")
    public ResponseEntity<FormValidationSummaryResponse> getAllForUser(@PathVariable Long userInfoId) {
        return ResponseEntity.ok(service.getAllForUser(userInfoId));
    }

    /**
     * Atualiza o status de uma seção específica do formulário do aluno. Apenas admins
     * (validação cross-field via {@code reviewerId}).
     */
    @PutMapping("/{userInfoId}/{section}")
    public ResponseEntity<FormValidationStatusResponse> updateStatus(
            @PathVariable Long userInfoId,
            @PathVariable FormSectionEnum section,
            @Valid @RequestBody FormValidationStatusRequest request) {
        return ResponseEntity.ok(service.updateStatus(userInfoId, section, request));
    }
}
