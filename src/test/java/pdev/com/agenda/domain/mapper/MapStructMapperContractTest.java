package pdev.com.agenda.domain.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pdev.com.agenda.domain.dto.EscolaDTO;
import pdev.com.agenda.domain.dto.FornecimentoEnergiaDTOEnum;
import pdev.com.agenda.domain.dto.FormValidationStatusDTO;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.entity.FormValidationStatus;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.CondicoesDeSaudeCasosNaFamilia;
import pdev.com.agenda.domain.enuns.DoencasCronicas;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.FornecimentoEnergiaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapStructMapperContractTest {

    @Test
    void housingMapperMapsFrontendHealthFieldsWithoutChangingDomainMeaning() {
        FormCondicoesHabitacionaisMapper mapper = Mappers.getMapper(FormCondicoesHabitacionaisMapper.class);
        UserInfo user = new UserInfo();
        user.setId(18L);
        FormCondicoesHabitacionais entity = new FormCondicoesHabitacionais();
        entity.setSituacaoImovel(SituacaoImovelEnum.PROPRIO);
        entity.setTipoImovel(TipoImovelEnum.CASA);
        entity.setEstruturaFisica(EstruturaFisicaEnum.ALVENARIA);
        entity.setEsgotoSanitario(EsgotoSanitarioEnum.EXISTENTE);
        entity.setFornecimentoEnergia(FornecimentoEnergiaEnum.COMPANHIA_ELETRICA);
        entity.setAbastecimentoAgua(AbastecimentoAguaEnum.EXISTENTE);
        entity.setDoencasCronicas(DoencasCronicas.SIM);
        entity.setCondicoesDeSaudeCasosNaFamilia(CondicoesDeSaudeCasosNaFamilia.NAO);
        entity.setUser(user);

        var dto = mapper.toDTO(entity);
        assertEquals(18L, dto.userId());
        assertEquals(FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE, dto.fornecimentoEnergia());
        assertEquals(DoencasCronicas.SIM, dto.doencasCronicas());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO, dto.CondicoesDeSaudeCasosNaFamilia());

        FormCondicoesHabitacionais mappedEntity = mapper.toEntity(dto);
        assertEquals(entity.getSituacaoImovel(), mappedEntity.getSituacaoImovel());
        assertEquals(entity.getTipoImovel(), mappedEntity.getTipoImovel());
        assertEquals(entity.getEstruturaFisica(), mappedEntity.getEstruturaFisica());
        assertEquals(entity.getEsgotoSanitario(), mappedEntity.getEsgotoSanitario());
        assertEquals(entity.getFornecimentoEnergia(), mappedEntity.getFornecimentoEnergia());
        assertEquals(entity.getAbastecimentoAgua(), mappedEntity.getAbastecimentoAgua());
        assertEquals(entity.getDoencasCronicas(), mappedEntity.getDoencasCronicas());
        assertEquals(entity.getCondicoesDeSaudeCasosNaFamilia(),
                mappedEntity.getCondicoesDeSaudeCasosNaFamilia());
    }

    @Test
    void schoolMapperPreservesAllFieldsAndPartialUpdateSemantics() {
        EscolaMapper mapper = Mappers.getMapper(EscolaMapper.class);
        EscolaDTO dto = new EscolaDTO(
                7L, "Escola A", "12.345.678/0001-90", "NSU-7",
                "Rua A", TipoEscolaEnum.PARTICULAR, "ATIVO"
        );

        Escola entity = mapper.toEntity(dto);
        assertEquals(dto, mapper.toDTO(entity));

        EscolaDTO patch = new EscolaDTO(
                null, null, "98.765.432/0001-10", null,
                null, null, null
        );
        mapper.updateEntityFromDto(patch, entity);

        assertEquals(7L, entity.getId());
        assertEquals("Escola A", entity.getNome());
        assertEquals("98.765.432/0001-10", entity.getCnpj());
        assertEquals("NSU-7", entity.getNsu());
        assertEquals("Rua A", entity.getEndereco());
        assertEquals(TipoEscolaEnum.PARTICULAR, entity.getTipo());
        assertEquals("ATIVO", entity.getStatus());
    }

    @Test
    void validationStatusMapperPreservesForeignKeysEnumsAndReviewTimestamp() {
        FormValidationStatusMapper mapper = Mappers.getMapper(FormValidationStatusMapper.class);
        UserInfo student = new UserInfo();
        student.setId(17L);
        UserInfo reviewer = new UserInfo();
        reviewer.setId(21L);
        LocalDateTime reviewedAt = LocalDateTime.of(2026, 10, 15, 13, 45, 30);
        FormValidationStatus entity = new FormValidationStatus();
        entity.setId(9L);
        entity.setUserInfo(student);
        entity.setReviewer(reviewer);
        entity.setSection(FormSectionEnum.PERSONAL_DATA);
        entity.setStatus(ValidationStatusEnum.APPROVED);
        entity.setReviewedAt(reviewedAt);
        entity.setComment("Conferido");

        FormValidationStatusDTO dto = mapper.toResponse(entity);

        assertEquals(9L, dto.id());
        assertEquals(17L, dto.userInfoId());
        assertEquals(21L, dto.reviewerId());
        assertEquals(FormSectionEnum.PERSONAL_DATA, dto.section());
        assertEquals(ValidationStatusEnum.APPROVED, dto.status());
        assertEquals(reviewedAt, dto.reviewedAt());
        assertEquals("Conferido", dto.comment());
        assertNull(mapper.toResponse(null));
    }
}
