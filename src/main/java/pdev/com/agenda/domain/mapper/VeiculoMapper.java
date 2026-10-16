package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.VeiculoDTO;
import pdev.com.agenda.domain.entity.Veiculo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VeiculoMapper {

    @Mapping(target = "bensPosses", ignore = true)
    @Mapping(target = "status", ignore = true)
    Veiculo toEntity(VeiculoDTO dto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"bensPosses", "status"})
    VeiculoDTO toDTO(Veiculo entity);
}
