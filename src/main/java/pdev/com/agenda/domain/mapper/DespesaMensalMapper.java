package pdev.com.agenda.domain.mapper;


import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.DespesaMensalDTO;
import pdev.com.agenda.domain.entity.DespesaMensal;

@Component
public class DespesaMensalMapper {

    public DespesaMensal toEntity(DespesaMensalDTO dto) {
        if (dto == null) return null;
        DespesaMensal entity = new DespesaMensal();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        return entity;
    }

    public DespesaMensalDTO toDTO(DespesaMensal entity) {
        if (entity == null) return null;
        DespesaMensalDTO dto = new DespesaMensalDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setValor(entity.getValor());
        return dto;
    }
}