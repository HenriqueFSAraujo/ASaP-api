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

        FormDadosParentesDTO dto = new FormDadosParentesDTO();
        dto.setId(entity.getId());
        dto.setParent1Cpf(entity.getParent1Cpf());
        dto.setParent1FullName(entity.getParent1FullName());
        dto.setParent1Phone(entity.getParent1Phone());
        dto.setParent1MaritalStatus(entity.getParent1MaritalStatus());
        dto.setParent2Cpf(entity.getParent2Cpf());
        dto.setParent2FullName(entity.getParent2FullName());
        dto.setParent2Phone(entity.getParent2Phone());
        dto.setParent2MaritalStatus(entity.getParent2MaritalStatus());
        dto.setResidesWithBothParents(entity.getResidesWithBothParents());

        UserInfo user = new UserInfo();
        user.setId(entity.getUser().getId());
        dto.setUserId(user.getId());

        return dto;
    }

    public FormDadosParentes toEntity(FormDadosParentesDTO dto) {
        if (dto == null) {
            return null;
        }

        FormDadosParentes entity = new FormDadosParentes();
        entity.setId(dto.getId());
        entity.setParent1Cpf(dto.getParent1Cpf());
        entity.setParent1FullName(dto.getParent1FullName());
        entity.setParent1Phone(dto.getParent1Phone());
        entity.setParent1MaritalStatus(dto.getParent1MaritalStatus());
        entity.setParent2Cpf(dto.getParent2Cpf());
        entity.setParent2FullName(dto.getParent2FullName());
        entity.setParent2Phone(dto.getParent2Phone());
        entity.setParent2MaritalStatus(dto.getParent2MaritalStatus());
        entity.setResidesWithBothParents(dto.getResidesWithBothParents());

        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());
        entity.setUser(user);

        return entity;
    }

    public void updateEntityFromDTO(FormDadosParentes entity, FormDadosParentesDTO dto) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getParent1Cpf() != null) {
            entity.setParent1Cpf(dto.getParent1Cpf());
        }
        if (dto.getParent1FullName() != null) {
            entity.setParent1FullName(dto.getParent1FullName());
        }
    }
}