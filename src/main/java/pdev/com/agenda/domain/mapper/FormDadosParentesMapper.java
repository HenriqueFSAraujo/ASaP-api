package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FormDadosParentesDTO;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
public class FormDadosParentesMapper {

    public FormDadosParentesDTO toDTO(FormDadosParentes entity) {
        if (entity == null) {
            return null;
        }

        return new FormDadosParentesDTO(
                entity.getId(),
                entity.getParent1Cpf(),
                entity.getParent1FullName(),
                entity.getParent1Phone(),
                entity.getParent1MaritalStatus(),
                entity.getParent2Cpf(),
                entity.getParent2FullName(),
                entity.getParent2Phone(),
                entity.getParent2MaritalStatus(),
                entity.getResidesWithBothParents(),
                entity.getUser().getId()
        );
    }

    public FormDadosParentes toEntity(FormDadosParentesDTO dto) {
        if (dto == null) {
            return null;
        }

        FormDadosParentes entity = new FormDadosParentes();
        entity.setId(dto.id());
        entity.setParent1Cpf(dto.parent1Cpf());
        entity.setParent1FullName(dto.parent1FullName());
        entity.setParent1Phone(dto.parent1Phone());
        entity.setParent1MaritalStatus(dto.parent1MaritalStatus());
        entity.setParent2Cpf(dto.parent2Cpf());
        entity.setParent2FullName(dto.parent2FullName());
        entity.setParent2Phone(dto.parent2Phone());
        entity.setParent2MaritalStatus(dto.parent2MaritalStatus());
        entity.setResidesWithBothParents(dto.residesWithBothParents());

        UserInfo user = new UserInfo();
        user.setId(dto.userId());
        entity.setUser(user);

        return entity;
    }

    public void updateEntityFromDTO(FormDadosParentes entity, FormDadosParentesDTO dto) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.parent1Cpf() != null) {
            entity.setParent1Cpf(dto.parent1Cpf());
        }
        if (dto.parent1FullName() != null) {
            entity.setParent1FullName(dto.parent1FullName());
        }
    }
}