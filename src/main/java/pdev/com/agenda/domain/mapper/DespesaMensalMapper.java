package pdev.com.agenda.domain.mapper;


import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.DespesaMensalDTO;
import pdev.com.agenda.domain.entity.DespesaMensal;

@Component
public class DespesaMensalMapper {

    public DespesaMensal toEntity(DespesaMensalDTO dto) {
        if (dto == null) return null;
        DespesaMensal entity = new DespesaMensal();
        entity.setId(dto.id());
        entity.setDescricao(dto.descricao());
        entity.setValor(dto.valor());
        return entity;
    }

    public DespesaMensalDTO toDTO(DespesaMensal entity) {
        if (entity == null) return null;
        return new DespesaMensalDTO(entity.getId(), entity.getDescricao(), entity.getValor());
    }
}