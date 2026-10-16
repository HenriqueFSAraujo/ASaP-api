package pdev.com.agenda.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mapstruct.factory.Mappers;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.dto.FornecimentoEnergiaDTOEnum;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.CondicoesDeSaudeCasosNaFamilia;
import pdev.com.agenda.domain.enuns.DoencasCronicas;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.FornecimentoEnergiaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;
import pdev.com.agenda.domain.mapper.FormCondicoesHabitacionaisMapper;
import pdev.com.agenda.domain.repository.FormCondicoesHabitacionaisRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormCondicoesHabitacionaisServiceMappingTest {

    @Mock
    private FormCondicoesHabitacionaisRepository repository;

    private FormCondicoesHabitacionaisService service;

    @BeforeEach
    void setUp() {
        FormCondicoesHabitacionaisMapper mapper = Mappers.getMapper(FormCondicoesHabitacionaisMapper.class);
        service = new FormCondicoesHabitacionaisService(repository, mapper);
    }

    @Test
    void createsHousingRecordWithFrontendHealthMeaningAndExistingStatusRule() {
        CondicoesHabitacionaisDTO request = frontendPayload();
        when(repository.findByUser(any(UserInfo.class))).thenReturn(Optional.empty());
        when(repository.save(any(FormCondicoesHabitacionais.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CondicoesHabitacionaisDTO response = service.salvarOuAtualizar(request);

        ArgumentCaptor<FormCondicoesHabitacionais> saved =
                ArgumentCaptor.forClass(FormCondicoesHabitacionais.class);
        verify(repository).save(saved.capture());
        assertEquals(12L, saved.getValue().getUser().getId());
        assertEquals("ATIVO", saved.getValue().getStatus());
        assertEquals(FornecimentoEnergiaEnum.COMPANHIA_ELETRICA,
                saved.getValue().getFornecimentoEnergia());
        assertEquals(DoencasCronicas.SIM, saved.getValue().getDoencasCronicas());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO,
                saved.getValue().getCondicoesDeSaudeCasosNaFamilia());
        assertEquals(DoencasCronicas.SIM, response.doencasCronicas());
        assertEquals(FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE,
                response.fornecimentoEnergia());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO,
                response.CondicoesDeSaudeCasosNaFamilia());
    }

    @Test
    void upsertPreservesExistingIdUserAndStatusWhileMappingAllFormFields() {
        CondicoesHabitacionaisDTO request = frontendPayload();
        UserInfo existingUser = new UserInfo();
        existingUser.setId(12L);
        FormCondicoesHabitacionais existing = new FormCondicoesHabitacionais();
        existing.setId(31L);
        existing.setUser(existingUser);
        existing.setStatus("REVIEWED");
        when(repository.findByUser(any(UserInfo.class))).thenReturn(Optional.of(existing));
        when(repository.save(any(FormCondicoesHabitacionais.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CondicoesHabitacionaisDTO response = service.salvarOuAtualizar(request);

        ArgumentCaptor<FormCondicoesHabitacionais> saved =
                ArgumentCaptor.forClass(FormCondicoesHabitacionais.class);
        verify(repository).save(saved.capture());
        assertEquals(31L, saved.getValue().getId());
        assertSame(existingUser, saved.getValue().getUser());
        assertEquals("REVIEWED", saved.getValue().getStatus());
        assertEquals(FornecimentoEnergiaEnum.COMPANHIA_ELETRICA,
                saved.getValue().getFornecimentoEnergia());
        assertEquals(DoencasCronicas.SIM, saved.getValue().getDoencasCronicas());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO,
                saved.getValue().getCondicoesDeSaudeCasosNaFamilia());
        assertEquals(12L, response.userId());
        assertEquals(FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE,
                response.fornecimentoEnergia());
    }

    @Test
    void explicitUpdateRetainsPendingStatusTransition() {
        FormCondicoesHabitacionais existing = new FormCondicoesHabitacionais();
        existing.setId(31L);
        existing.setStatus("REVIEWED");
        when(repository.findById(31L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        FormCondicoesHabitacionais response = service.update(31L, frontendPayload());

        assertSame(existing, response);
        assertEquals("PENDENTE", response.getStatus());
        assertEquals(DoencasCronicas.SIM, response.getDoencasCronicas());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO,
                response.getCondicoesDeSaudeCasosNaFamilia());
    }

    @Test
    void getByUserIdReturnsFrontendDtoFromPersistedEntity() {
        UserInfo user = new UserInfo();
        user.setId(12L);
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
        when(repository.findByUser_Id(12L)).thenReturn(Optional.of(entity));

        CondicoesHabitacionaisDTO response = service.getByUserId(12L).orElseThrow();

        assertEquals(12L, response.userId());
        assertEquals(FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE,
                response.fornecimentoEnergia());
        assertEquals(DoencasCronicas.SIM, response.doencasCronicas());
        assertEquals(CondicoesDeSaudeCasosNaFamilia.NAO,
                response.CondicoesDeSaudeCasosNaFamilia());
    }

    private CondicoesHabitacionaisDTO frontendPayload() {
        return new CondicoesHabitacionaisDTO(
                SituacaoImovelEnum.PROPRIO,
                TipoImovelEnum.CASA,
                EstruturaFisicaEnum.ALVENARIA,
                EsgotoSanitarioEnum.EXISTENTE,
                FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE,
                AbastecimentoAguaEnum.EXISTENTE,
                CondicoesDeSaudeCasosNaFamilia.NAO,
                DoencasCronicas.SIM,
                12L
        );
    }
}
