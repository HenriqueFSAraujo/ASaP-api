package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
public class FormDadosPessoaisMapper {

    public FormDadosPessoais toEntity(FormDadosPessoaisDTO dto) {
        if (dto == null) {
            return null;
        }
        FormDadosPessoais entity = new FormDadosPessoais();
        entity.setId(dto.id());
        entity.setFullName(dto.fullName());
        entity.setCpf(dto.cpf());
        entity.setRg(dto.rg());
        entity.setNacionalidade(dto.nacionalidade());
        entity.setNaturalidade(dto.naturalidade());
        entity.setCor(dto.cor());
        entity.setPhone(dto.phone());
        entity.setGender(dto.gender());
        entity.setCpfBolsista(dto.cpfBolsista());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setNumEducasenso(dto.numEducasenso());
        entity.setPcd(dto.pcd());
        entity.setStatus(dto.status());
        UserInfo user = new UserInfo();
        user.setId(dto.userId());
        entity.setUser(user);
        if (dto.escolaId() != null) {
            Escola escola = new Escola();
            escola.setId(dto.escolaId());
            entity.setEscola(escola);
        }
        return entity;
    }

    public FormDadosPessoaisDTO toDto(FormDadosPessoais entity) {
        if (entity == null) {
            return null;
        }
        return new FormDadosPessoaisDTO(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getRg(),
                entity.getNacionalidade(),
                entity.getNaturalidade(),
                entity.getCor(),
                entity.getPhone(),
                entity.getGender(),
                entity.getCpfBolsista(),
                entity.getDataNascimento(),
                entity.getPcd(),
                entity.getNumEducasenso(),
                entity.getStatus(),
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getEscola() != null ? entity.getEscola().getId() : null,
                entity.getEscola() != null ? entity.getEscola().getTipo() : null
        );
    }
}
