package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.dto.FormValidationStatusRequest;
import pdev.com.agenda.domain.dto.FormValidationStatusResponse;
import pdev.com.agenda.domain.dto.FormValidationSummaryResponse;
import pdev.com.agenda.domain.entity.FormValidationStatus;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.enuns.RoleEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;
import pdev.com.agenda.domain.mapper.FormValidationStatusMapper;
import pdev.com.agenda.domain.repository.FormValidationStatusRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gerencia o status de validação do admin sobre cada seção do formulário do aluno.
 * <p>
 * Convenções:
 * <ul>
 *   <li>{@code getAllForUser} sempre retorna as 8 seções — inicializa em memória como PENDING
 *       as que ainda não existem em banco (não escreve nada).</li>
 *   <li>{@code updateStatus} faz upsert (cria se não existir).</li>
 *   <li>{@code allApproved} = true quando TODAS as 8 seções estão em {@code APPROVED}.</li>
 *   <li>Só admins podem alterar — validação feita via {@code reviewerId} no payload (futuramente
 *       será via JWT/SecurityContext).</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class FormValidationStatusService {

    private final FormValidationStatusRepository repository;
    private final UserInfoRepository userInfoRepository;
    private final FormValidationStatusMapper mapper;

    @Transactional(readOnly = true)
    public FormValidationSummaryResponse getAllForUser(Long userInfoId) {
        if (!userInfoRepository.existsById(userInfoId)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + userInfoId);
        }

        // Mapeia o que já existe em banco por seção, para preencher os "buracos" com PENDING virtual.
        Map<FormSectionEnum, FormValidationStatus> existingBySection = new HashMap<>();
        for (FormValidationStatus fvs : repository.findAllByUserInfo_Id(userInfoId)) {
            existingBySection.put(fvs.getSection(), fvs);
        }

        List<FormValidationStatusResponse> sections = new ArrayList<>(FormSectionEnum.values().length);
        boolean allApproved = true;
        for (FormSectionEnum section : FormSectionEnum.values()) {
            FormValidationStatus fvs = existingBySection.get(section);
            if (fvs == null) {
                sections.add(buildPendingResponse(userInfoId, section));
                allApproved = false;
            } else {
                sections.add(mapper.toResponse(fvs));
                if (fvs.getStatus() != ValidationStatusEnum.APPROVED) {
                    allApproved = false;
                }
            }
        }

        return FormValidationSummaryResponse.builder()
                .userInfoId(userInfoId)
                .sections(sections)
                .allApproved(allApproved)
                .build();
    }

    @Transactional
    public FormValidationStatusResponse updateStatus(Long userInfoId,
                                                     FormSectionEnum section,
                                                     FormValidationStatusRequest request) {
        UserInfo user = userInfoRepository.findById(userInfoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado com ID: " + userInfoId));

        UserInfo reviewer = resolveReviewer(request.getReviewerId());

        FormValidationStatus entity = repository.findByUserInfo_IdAndSection(userInfoId, section)
                .orElseGet(() -> {
                    FormValidationStatus novo = new FormValidationStatus();
                    novo.setUserInfo(user);
                    novo.setSection(section);
                    return novo;
                });

        entity.setStatus(request.getStatus());
        entity.setReviewer(reviewer);
        entity.setReviewedAt(LocalDateTime.now());
        entity.setComment(request.getComment());

        return mapper.toResponse(repository.save(entity));
    }

    /**
     * Valida e retorna o reviewer (admin). Se nulo, retorna null (registro fica sem reviewer
     * mas com reviewedAt). Se inválido (não é admin), lança erro.
     */
    private UserInfo resolveReviewer(Long reviewerId) {
        if (reviewerId == null) {
            return null;
        }
        UserInfo reviewer = userInfoRepository.findById(reviewerId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reviewer não encontrado com ID: " + reviewerId));

        boolean isAdmin = reviewer.getRole() != null
                && reviewer.getRole().getName() == RoleEnum.ROLE_ADMIN;
        if (!isAdmin) {
            throw new IllegalArgumentException(
                    "Apenas usuários ADMIN podem alterar status de validação.");
        }
        return reviewer;
    }

    private FormValidationStatusResponse buildPendingResponse(Long userInfoId, FormSectionEnum section) {
        return FormValidationStatusResponse.builder()
                .userInfoId(userInfoId)
                .section(section)
                .status(ValidationStatusEnum.PENDING)
                .build();
    }
}
