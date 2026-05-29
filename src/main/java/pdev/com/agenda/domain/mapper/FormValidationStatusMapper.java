package pdev.com.agenda.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.FormValidationStatusResponse;
import pdev.com.agenda.domain.entity.FormValidationStatus;

/**
 * Mapper MapStruct para a entidade {@link FormValidationStatus}.
 * Segue o padrão estabelecido em {@code §12.1} do CLAUDE.md.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FormValidationStatusMapper {

    @Mapping(target = "userInfoId", source = "userInfo.id")
    @Mapping(target = "reviewerId", source = "reviewer.id")
    FormValidationStatusResponse toResponse(FormValidationStatus entity);
}
