package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FamiliarEscolaParticularDTO;
import pdev.com.agenda.domain.entity.FamiliarEscolaParticular;

@Component
public class FamiliarEscolaParticularMapper {

    public FamiliarEscolaParticular toEntity(FamiliarEscolaParticularDTO dto) {
        if (dto == null) return null;
        FamiliarEscolaParticular entity = new FamiliarEscolaParticular();
        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setEscola(dto.escola());
        entity.setValorMensal(dto.valorMensal());
        return entity;
    }

    public FamiliarEscolaParticularDTO toDTO(FamiliarEscolaParticular entity) {
        if (entity == null) return null;
        return new FamiliarEscolaParticularDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEscola(),
                entity.getValorMensal()
        );
    }
}
