package pdev.com.agenda.domain.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.flywaydb.core.Flyway;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.enuns.AbastecimentoAguaEnum;
import pdev.com.agenda.domain.enuns.CondicoesDeSaudeCasosNaFamilia;
import pdev.com.agenda.domain.enuns.DoencasCronicas;
import pdev.com.agenda.domain.enuns.EsgotoSanitarioEnum;
import pdev.com.agenda.domain.enuns.EstruturaFisicaEnum;
import pdev.com.agenda.domain.enuns.SituacaoImovelEnum;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.domain.enuns.TipoImovelEnum;
import pdev.com.agenda.domain.service.FormCondicoesHabitacionaisService;
import pdev.com.agenda.domain.service.FormService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class DtoValidationHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Flyway flyway;

    @MockitoBean
    private FormService formService;

    @MockitoBean
    private FormCondicoesHabitacionaisService housingService;

    static Stream<RequestCase> requestBodiesWithRequiredFields() {
        return Stream.of(
                new RequestCase(HttpMethod.POST, "/api/users"),
                new RequestCase(HttpMethod.POST, "/api/escolas"),
                new RequestCase(HttpMethod.POST, "/api/forms"),
                new RequestCase(HttpMethod.POST, "/api/parentes"),
                new RequestCase(HttpMethod.POST, "/api/enderecos"),
                new RequestCase(HttpMethod.POST, "/api/form-condicoes-habitacionais"),
                new RequestCase(HttpMethod.POST, "/api/bens-posses"),
                new RequestCase(HttpMethod.POST, "/api/processo-bolsas"),
                new RequestCase(HttpMethod.POST, "/api/parecer-socioeconomico"),
                new RequestCase(HttpMethod.POST, "/api/composicao-familiar"),
                new RequestCase(HttpMethod.POST, "/api/declaracoes"),
                new RequestCase(HttpMethod.PUT, "/api/form-validation/1/PERSONAL_DATA"),
                new RequestCase(HttpMethod.PUT, "/api/users/status/1")
        );
    }

    @ParameterizedTest
    @MethodSource("requestBodiesWithRequiredFields")
    void requiredFieldsAreValidatedAtHttpBoundary(RequestCase requestCase) throws Exception {
        MockHttpServletRequestBuilder request = request(requestCase.method(), requestCase.path())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors").isArray())
                .andExpect(jsonPath("$.fieldErrors").isNotEmpty());
    }

    @Test
    void malformedEnumReturnsBadRequestInsteadOfInternalServerError() throws Exception {
        mockMvc.perform(request(HttpMethod.PUT, "/api/form-validation/1/PERSONAL_DATA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"INVALID\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Corpo da requisição inválido."));
    }

    @Test
    void conditionalScholarshipPercentageIsValidatedAtHttpBoundary() throws Exception {
        mockMvc.perform(post("/api/processo-bolsas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "vaiParticipar": true,
                                  "jaFoiContemplado": true,
                                  "userId": 1,
                                  "segmentoAno": "Ensino Fundamental",
                                  "serieAno": "5º ano"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field").value("percentual"));
    }

    @Test
    void frontEndDateFormatsAreDeserializedWithoutDateValidation() throws Exception {
        mockMvc.perform(post("/api/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": 1,
                                  "fullName": "Ana",
                                  "email": "invalid-email",
                                  "cpf": "123",
                                  "nacionalidade": "Brasileira",
                                  "naturalidade": "Recife",
                                  "cor": "Parda",
                                  "phone": "81999999999",
                                  "gender": "Feminino",
                                  "dateBirth": "2000-04-15T00:00:00.000Z",
                                  "deficiency": "Não",
                                  "escolaId": 1,
                                  "tipoEscola": "PARTICULAR"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field").value("email"));

        mockMvc.perform(post("/api/composicao-familiar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "composicaoFamiliar": [
                                    { "dataNascimento": "15/04/2000" }
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field").value("userInfoId"));
    }

    @Test
    void validSchoolPayloadIsTransferredAndReturned() throws Exception {
        String cnpj = "12.345.678/" + UUID.randomUUID().toString().replace("-", "").substring(0, 4)
                + "-90";
        mockMvc.perform(post("/api/escolas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Escola DTO Teste",
                                  "cnpj": "%s",
                                  "nsu": "NSU-DTO",
                                  "endereco": "Rua Teste, 10",
                                  "tipo": "PARTICULAR",
                                  "status": "ATIVO"
                                }
                                """.formatted(cnpj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Escola DTO Teste"))
                .andExpect(jsonPath("$.cnpj").value(cnpj))
                .andExpect(jsonPath("$.nsu").value("NSU-DTO"))
                .andExpect(jsonPath("$.endereco").value("Rua Teste, 10"))
                .andExpect(jsonPath("$.tipo").value("PARTICULAR"))
                .andExpect(jsonPath("$.status").value("ATIVO"));
    }

    @Test
    void frontendPersonalPayloadPostsAndGetsWithFrontendWireNamesWithoutRequestSchoolType() throws Exception {
        FormDadosPessoaisDTO savedDto = new FormDadosPessoaisDTO(
                3L, "Ana Silva", "", "12345678901", "RG123",
                "Brasileira", "Recife", "Parda", "81999990000", "Feminino",
                "", LocalDate.of(2000, 4, 15), "Não", "EDU123", "PENDENTE",
                12L, 8L, TipoEscolaEnum.PARTICULAR
        );
        when(formService.createDadosPessoais(any(FormDadosPessoaisDTO.class)))
                .thenAnswer(invocation -> savedDto);
        when(formService.getByUserId(12L)).thenReturn(savedDto);

        String payload = """
                {
                  "userId": 12,
                  "fullName": "Ana Silva",
                  "email": "",
                  "cpf": "12345678901",
                  "rg": "RG123",
                  "nationality": "Brasileira",
                  "birthplace": "Recife",
                  "race": "Parda",
                  "phone": "81999990000",
                  "gender": "Feminino",
                  "cpfScholarship": "",
                  "dateBirth": "2000-04-15T00:00:00.000Z",
                  "deficiency": "Não",
                  "educasenso": "EDU123",
                  "escolaId": 8
                }
                """;

        mockMvc.perform(post("/api/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nationality").value("Brasileira"))
                .andExpect(jsonPath("$.birthplace").value("Recife"))
                .andExpect(jsonPath("$.race").value("Parda"))
                .andExpect(jsonPath("$.dateBirth").value("2000-04-15"))
                .andExpect(jsonPath("$.tipoEscola").value("PARTICULAR"))
                .andExpect(jsonPath("$.nacionalidade").doesNotExist())
                .andExpect(jsonPath("$.naturalidade").doesNotExist())
                .andExpect(jsonPath("$.cor").doesNotExist());

        ArgumentCaptor<FormDadosPessoaisDTO> personalDto = ArgumentCaptor.forClass(FormDadosPessoaisDTO.class);
        verify(formService).createDadosPessoais(personalDto.capture());
        org.junit.jupiter.api.Assertions.assertNull(personalDto.getValue().tipoEscola());
        org.junit.jupiter.api.Assertions.assertEquals(8L, personalDto.getValue().escolaId());

        mockMvc.perform(request(HttpMethod.GET, "/api/forms/12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nationality").value("Brasileira"))
                .andExpect(jsonPath("$.birthplace").value("Recife"))
                .andExpect(jsonPath("$.race").value("Parda"))
                .andExpect(jsonPath("$.dateBirth").value("2000-04-15"))
                .andExpect(jsonPath("$.tipoEscola").value("PARTICULAR"));
    }

    @Test
    void frontendHousingPayloadPostsAndGetsBothHealthAnswersWithFrontendWireNames() throws Exception {
        CondicoesHabitacionaisDTO savedDto = new CondicoesHabitacionaisDTO(
                SituacaoImovelEnum.PROPRIO, TipoImovelEnum.CASA, EstruturaFisicaEnum.ALVENARIA,
                EsgotoSanitarioEnum.EXISTENTE, FornecimentoEnergiaDTOEnum.COMPANHIA_EXISTENTE,
                AbastecimentoAguaEnum.EXISTENTE, CondicoesDeSaudeCasosNaFamilia.NAO,
                DoencasCronicas.SIM, 12L
        );
        when(housingService.salvarOuAtualizar(any(CondicoesHabitacionaisDTO.class)))
                .thenAnswer(invocation -> savedDto);
        when(housingService.getByUserId(12L)).thenReturn(Optional.of(savedDto));

        String payload = """
                {
                  "userId": 12,
                  "situacaoImovel": "PROPRIO",
                  "tipoImovel": "CASA",
                  "estruturaFisica": "ALVENARIA",
                  "esgotoSanitario": "EXISTENTE",
                  "fornecimentoEnergia": "COMPANHIA_EXISTENTE",
                  "abastecimentoAgua": "EXISTENTE",
                  "doencaCronicaFamilia": "SIM",
                  "deficienciaFamilia": "NAO"
                }
                """;

        mockMvc.perform(post("/api/form-condicoes-habitacionais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fornecimentoEnergia").value("COMPANHIA_EXISTENTE"))
                .andExpect(jsonPath("$.doencaCronicaFamilia").value("SIM"))
                .andExpect(jsonPath("$.deficienciaFamilia").value("NAO"))
                .andExpect(jsonPath("$.doencasCronicas").doesNotExist())
                .andExpect(jsonPath("$.CondicoesDeSaudeCasosNaFamilia").doesNotExist());

        ArgumentCaptor<CondicoesHabitacionaisDTO> housingDto =
                ArgumentCaptor.forClass(CondicoesHabitacionaisDTO.class);
        verify(housingService).salvarOuAtualizar(housingDto.capture());
        org.junit.jupiter.api.Assertions.assertEquals(
                DoencasCronicas.SIM, housingDto.getValue().doencasCronicas());
        org.junit.jupiter.api.Assertions.assertEquals(
                CondicoesDeSaudeCasosNaFamilia.NAO,
                housingDto.getValue().CondicoesDeSaudeCasosNaFamilia());

        mockMvc.perform(request(HttpMethod.GET, "/api/form-condicoes-habitacionais/12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fornecimentoEnergia").value("COMPANHIA_EXISTENTE"))
                .andExpect(jsonPath("$.doencaCronicaFamilia").value("SIM"))
                .andExpect(jsonPath("$.deficienciaFamilia").value("NAO"));
    }

    record RequestCase(HttpMethod method, String path) {
    }
}
