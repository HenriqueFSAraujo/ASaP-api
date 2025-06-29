package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.PessoaComDeficienciaDTO;
import pdev.com.agenda.domain.entity.PessoaComDeficiencia;

@Component
public class PessoaComDeficienciaMapper {

    public PessoaComDeficiencia toEntity(PessoaComDeficienciaDTO dto) {
        if (dto == null) return null;
        PessoaComDeficiencia entity = new PessoaComDeficiencia();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setTipoDeficiencia(dto.getTipoDeficiencia());
        entity.setDespesaMensal(dto.getDespesaMensal());
        return entity;
    }

    public PessoaComDeficienciaDTO toDTO(PessoaComDeficiencia entity) {
        if (entity == null) return null;
        PessoaComDeficienciaDTO dto = new PessoaComDeficienciaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setTipoDeficiencia(entity.getTipoDeficiencia());
        dto.setDespesaMensal(entity.getDespesaMensal());
        return dto;
    }
}