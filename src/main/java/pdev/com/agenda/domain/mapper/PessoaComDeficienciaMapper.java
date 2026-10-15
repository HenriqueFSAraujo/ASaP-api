package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.PessoaComDeficienciaDTO;
import pdev.com.agenda.domain.entity.PessoaComDeficiencia;

@Component
public class PessoaComDeficienciaMapper {

    public PessoaComDeficiencia toEntity(PessoaComDeficienciaDTO dto) {
        if (dto == null) return null;
        PessoaComDeficiencia entity = new PessoaComDeficiencia();
        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setTipoDeficiencia(dto.tipoDeficiencia());
        entity.setDespesaMensal(dto.despesaMensal());
        return entity;
    }

    public PessoaComDeficienciaDTO toDTO(PessoaComDeficiencia entity) {
        if (entity == null) return null;
        return new PessoaComDeficienciaDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTipoDeficiencia(),
                entity.getDespesaMensal()
        );
    }
}