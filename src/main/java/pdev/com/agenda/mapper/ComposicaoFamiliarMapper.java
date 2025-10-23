package pdev.com.agenda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.dto.ComposicaoFamiliarRequestDTO;

import java.util.List;

@Mapper
public interface ComposicaoFamiliarMapper {

    ComposicaoFamiliarMapper INSTANCE = Mappers.getMapper(ComposicaoFamiliarMapper.class);

    @Mapping(source = "userInfo.id", target = "userId")
    ComposicaoFamiliarRequestDTO toDTO(ComposicaoFamiliar entity);

    @Mapping(source = "userInfo", target = "userInfo.id")
    ComposicaoFamiliar toEntity(ComposicaoFamiliarRequestDTO dto);

    List<ComposicaoFamiliarDTO> toDTOList(List<ComposicaoFamiliar> entities);

    List<ComposicaoFamiliar> toEntityList(List<ComposicaoFamiliarDTO> dtos);
}
