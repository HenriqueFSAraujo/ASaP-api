package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FamiliarEscolaParticularDTO;
import pdev.com.agenda.domain.entity.FamiliarEscolaParticular;

@Component
public class FamiliarEscolaParticularMapper {

    public FamiliarEscolaParticular toEntity(FamiliarEscolaParticularDTO dto) {
        if (dto == null) return null;
        FamiliarEscolaParticular entity = new FamiliarEscolaParticular();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEscola(dto.getEscola());
        entity.setValorMensal(dto.getValorMensal());
        return entity;
    }

    public FamiliarEscolaParticularDTO toDTO(FamiliarEscolaParticular entity) {
        if (entity == null) return null;
        FamiliarEscolaParticularDTO dto = new FamiliarEscolaParticularDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEscola(entity.getEscola());
        dto.setValorMensal(entity.getValorMensal());
        return dto;
    }
}
