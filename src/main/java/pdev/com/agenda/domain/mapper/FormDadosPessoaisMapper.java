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
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setNacionalidade(dto.getNacionalidade());
        entity.setNaturalidade(dto.getNaturalidade());
        entity.setCor(dto.getCor());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setCpfBolsista(dto.getCpfBolsista());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setNumEducasenso(dto.getNumEducasenso());
        entity.setPcd(dto.getPcd());
        entity.setStatus(dto.getStatus());
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
        dto.setCpf(entity.getCpf());
        dto.setRg(entity.getRg());
        dto.setNacionalidade(entity.getNacionalidade());
        dto.setNaturalidade(entity.getNaturalidade());
        dto.setCor(entity.getCor());
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setCpfBolsista(entity.getCpfBolsista());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setNumEducasenso(entity.getNumEducasenso());
        dto.setPcd(entity.getPcd());
        dto.setStatus(entity.getStatus());
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        return dto;
    }
}
