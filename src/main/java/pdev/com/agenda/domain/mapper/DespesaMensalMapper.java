package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.DespesaMensalDTO;
import pdev.com.agenda.domain.entity.DespesaMensal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DespesaMensalMapper {

    @Mapping(target = "bensPosses", ignore = true)
    @Mapping(target = "status", ignore = true)
    DespesaMensal toEntity(DespesaMensalDTO dto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"bensPosses", "status"})
    DespesaMensalDTO toDTO(DespesaMensal entity);
}
