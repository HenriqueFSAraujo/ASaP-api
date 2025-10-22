package pdev.com.agenda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;

import java.util.List;

@Mapper
public interface ComposicaoFamiliarMapper {

    ComposicaoFamiliarMapper INSTANCE = Mappers.getMapper(ComposicaoFamiliarMapper.class);

    ComposicaoFamiliarDTO toDTO(ComposicaoFamiliar entity);

    ComposicaoFamiliar toEntity(ComposicaoFamiliarDTO dto);

    List<ComposicaoFamiliarDTO> toDTOList(List<ComposicaoFamiliar> entities);

    List<ComposicaoFamiliar> toEntityList(List<ComposicaoFamiliarDTO> dtos);
}
