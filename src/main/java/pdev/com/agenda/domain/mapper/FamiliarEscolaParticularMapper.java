package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.FamiliarEscolaParticularDTO;
import pdev.com.agenda.domain.entity.FamiliarEscolaParticular;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FamiliarEscolaParticularMapper {

    @Mapping(target = "bensPosses", ignore = true)
    @Mapping(target = "status", ignore = true)
    FamiliarEscolaParticular toEntity(FamiliarEscolaParticularDTO dto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"bensPosses", "status"})
    FamiliarEscolaParticularDTO toDTO(FamiliarEscolaParticular entity);
}
