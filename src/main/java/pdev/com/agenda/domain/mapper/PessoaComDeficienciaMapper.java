package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.PessoaComDeficienciaDTO;
import pdev.com.agenda.domain.entity.PessoaComDeficiencia;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PessoaComDeficienciaMapper {

    @Mapping(target = "bensPosses", ignore = true)
    @Mapping(target = "status", ignore = true)
    PessoaComDeficiencia toEntity(PessoaComDeficienciaDTO dto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"bensPosses", "status"})
    PessoaComDeficienciaDTO toDTO(PessoaComDeficiencia entity);
}
