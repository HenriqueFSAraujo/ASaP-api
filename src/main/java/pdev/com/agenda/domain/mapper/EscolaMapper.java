package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.EscolaDTO;
import pdev.com.agenda.domain.entity.Escola;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EscolaMapper {

    Escola toEntity(EscolaDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(EscolaDTO dto, @MappingTarget Escola entity);

    EscolaDTO toDTO(Escola entity);
}
