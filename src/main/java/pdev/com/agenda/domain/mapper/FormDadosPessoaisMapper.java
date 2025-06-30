package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
public class FormDadosPessoaisMapper {

    public FormDadosPessoais toEntity(FormDadosPessoaisDTO dto) {
        if (dto == null) {
            return null;
        }

        FormDadosPessoais entity = new FormDadosPessoais();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setCpfBolsista(dto.getCpfBolsista());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setPcd(dto.getPcd());
        entity.setNumEducasenso(dto.getNumEducasenso());

        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());
        entity.setUser(user);
        return entity;
    }

    public FormDadosPessoaisDTO toDto(FormDadosPessoais entity) {
        if (entity == null) {
            return null;
        }

        FormDadosPessoaisDTO dto = new FormDadosPessoaisDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setLogin(entity.getLogin());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setCpfBolsista(entity.getCpfBolsista());
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setPcd(entity.getPcd());
        dto.setNumEducasenso(entity.getNumEducasenso());

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }

        return dto;
    }
}
