package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.dto.FornecimentoEnergiaDTOEnum;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.enuns.FornecimentoEnergiaEnum;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FormCondicoesHabitacionaisMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "condicoesDeSaudeCasosNaFamilia", source = "CondicoesDeSaudeCasosNaFamilia")
    FormCondicoesHabitacionais toEntity(CondicoesHabitacionaisDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "condicoesDeSaudeCasosNaFamilia", source = "CondicoesDeSaudeCasosNaFamilia")
    void updateEntityFromDTO(CondicoesHabitacionaisDTO dto,
                             @MappingTarget FormCondicoesHabitacionais entity);

    @BeanMapping(ignoreUnmappedSourceProperties = {"id", "status"})
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "CondicoesDeSaudeCasosNaFamilia", source = "condicoesDeSaudeCasosNaFamilia")
    CondicoesHabitacionaisDTO toDTO(FormCondicoesHabitacionais entity);

    default FornecimentoEnergiaEnum toEntity(FornecimentoEnergiaDTOEnum fornecimentoEnergia) {
        if (fornecimentoEnergia == null) {
            return null;
        }
        return switch (fornecimentoEnergia) {
            case COMPANHIA_EXISTENTE -> FornecimentoEnergiaEnum.COMPANHIA_ELETRICA;
            case INEXISTENTE -> FornecimentoEnergiaEnum.INEXISTENTE;
            case OUTRO -> FornecimentoEnergiaEnum.OUTRO;
            case GERADOR -> FornecimentoEnergiaEnum.GERADOR;
            case EXISTENTE -> FornecimentoEnergiaEnum.EXISTENTE;
        };
    }

    default FornecimentoEnergiaDTOEnum toDTO(FornecimentoEnergiaEnum fornecimentoEnergia) {
        if (fornecimentoEnergia == null) {
            return null;
        }
        return switch (fornecimentoEnergia) {
            case COMPANHIA_ELETRICA -> FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE;
            case INEXISTENTE -> FornecimentoEnergiaDTOEnum.INEXISTENTE;
            case OUTRO -> FornecimentoEnergiaDTOEnum.OUTRO;
            case GERADOR -> FornecimentoEnergiaDTOEnum.GERADOR;
            case EXISTENTE -> FornecimentoEnergiaDTOEnum.EXISTENTE;
        };
    }
}
