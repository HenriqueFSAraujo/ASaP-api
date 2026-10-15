package pdev.com.agenda.domain.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pdev.com.agenda.domain.enuns.TipoAlunoEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.Role;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;
import pdev.com.agenda.domain.mapper.UserInfoMapper;
import pdev.com.agenda.mapper.ComposicaoFamiliarMapper;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.dto.ComposicaoFamiliarRequestDTO;
import pdev.com.agenda.dto.DeclaracaoDTO;
import pdev.com.agenda.exception.ErrorResponse;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DtoRecordsTest {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .findAndAddModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        validatorFactory.close();
    }

    @Test
    void allTransportObjectsAreRecords() {
        List<Class<?>> types = List.of(
                BensPossesCompletoDTO.class,
                CondicoesHabitacionaisDTO.class,
                DespesaMensalDTO.class,
                DocumentosGeraisDTO.class,
                EscolaDTO.class,
                FamiliarEscolaParticularDTO.class,
                FormDadosParentesDTO.class,
                FormDadosPessoaisDTO.class,
                FormEnderecoCandidatoDTO.class,
                FormsStatusDTO.class,
                FormValidationStatusDTO.class,
                FormValidationSummaryResponse.class,
                ParecerSocioeconomicoDTO.class,
                PessoaComDeficienciaDTO.class,
                ProcessoDeBolsaDTO.class,
                ResetPasswordRequest.class,
                StatusDTO.class,
                UserInfoDTO.class,
                UserInfoWithStatusDTO.class,
                UserLoginRequest.class,
                UserLoginResponse.class,
                VeiculoDTO.class,
                ComposicaoFamiliarDTO.class,
                ComposicaoFamiliarRequestDTO.class,
                DeclaracaoDTO.class,
                ErrorResponse.class,
                ErrorResponse.FieldError.class
        );

        assertTrue(types.stream().allMatch(Class::isRecord));
    }

    @Test
    void recordJsonPreservesExistingPropertyNamesAndValues() throws Exception {
        FormDadosPessoaisDTO dto = new FormDadosPessoaisDTO(
                5L,
                "Ana Silva",
                "ana@example.com",
                "12345678901",
                "RG123",
                "Brasileira",
                "Recife",
                "Parda",
                "81999990000",
                "Feminino",
                "98765432100",
                LocalDate.of(2000, 4, 15),
                "Não",
                "EDU123",
                "PENDENTE",
                10L,
                2L,
                null
        );

        String json = OBJECT_MAPPER.writeValueAsString(dto);
        JsonNode body = OBJECT_MAPPER.readTree(json);

        assertEquals("Ana Silva", body.get("fullName").asText());
        assertEquals("98765432100", body.get("cpfScholarship").asText());
        assertEquals("2000-04-15", body.get("dateBirth").asText());
        assertFalse(body.has("cpfBolsista"));
        assertEquals(dto, OBJECT_MAPPER.readValue(json, FormDadosPessoaisDTO.class));
    }

    @Test
    void mergedRequestResponseRecordsRetainRequestAndResponseFields() throws Exception {
        String processoJson = """
                {
                  "id": 41,
                  "vaiParticipar": true,
                  "jaFoiContemplado": false,
                  "percentual": "50%",
                  "userId": 9,
                  "segmentoAno": "Fundamental",
                  "serieAno": "5º ano"
                }
                """;

        ProcessoDeBolsaDTO processo = OBJECT_MAPPER.readValue(processoJson, ProcessoDeBolsaDTO.class);
        assertNull(processo.id());
        assertTrue(processo.vaiParticipar());
        assertEquals(9L, processo.userId());

        FormValidationStatusDTO statusRequest = OBJECT_MAPPER.readValue(
                """
                        {
                          "id": 70,
                          "userInfoId": 9,
                          "section": "DADOS_PESSOAIS",
                          "status": "APPROVED",
                          "reviewerId": 2,
                          "reviewedAt": "2026-10-15T00:00:00",
                          "comment": "Conferido"
                        }
                        """,
                FormValidationStatusDTO.class
        );
        assertNull(statusRequest.id());
        assertNull(statusRequest.userInfoId());
        assertNull(statusRequest.section());
        assertNull(statusRequest.reviewedAt());
        assertEquals(ValidationStatusEnum.APPROVED, statusRequest.status());

        JsonNode response = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(
                new ProcessoDeBolsaDTO(41L, true, false, "50%", 9L, "Fundamental", "5º ano")));
        assertEquals(41L, response.get("id").asLong());
        assertEquals(9L, response.get("userId").asLong());
    }

    @Test
    void recordConstraintsRejectInvalidPayloadsAndAcceptValidOnes() {
        UserInfoDTO invalidUser = new UserInfoDTO(
                null, "", null, "INVALID_ROLE", "", "not-an-email",
                false, null, false, null
        );
        Set<String> invalidUserFields = validator.validate(invalidUser).stream()
                .map(violation -> violation.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertTrue(invalidUserFields.containsAll(Set.of("name", "roleName", "cpf", "email")));

        UserInfoDTO validUser = new UserInfoDTO(
                null, "Ana Silva", null, "ROLE_USER", "12345678901",
                "ana@example.com", false, null, false, TipoAlunoEnum.ESCOLA_PARTICULAR
        );
        assertTrue(validator.validate(validUser).isEmpty());
        assertEquals("novo@example.com", validUser.withEmail("novo@example.com").email());
        assertEquals("ana@example.com", validUser.email());

        EscolaDTO invalidSchool = new EscolaDTO(null, " ", "", null, null, null, null);
        assertEquals(5, validator.validate(invalidSchool).size());

        FormValidationStatusDTO missingStatus = new FormValidationStatusDTO(
                null, null, null, null, null, null, null
        );
        assertEquals(1, validator.validate(missingStatus).size());
    }

    @Test
    void requestDtoValidationCoversRequiredFieldsAndPreservesOptionalFields() {
        assertEquals(Set.of("nome", "cnpj", "endereco", "tipo"),
                fieldPaths(new EscolaDTO(null, null, null, null, null, null, null)));

        assertEquals(Set.of("fullName", "cpf", "nacionalidade", "naturalidade", "cor", "phone",
                        "gender", "pcd", "userId", "escolaId", "tipoEscola"),
                fieldPaths(new FormDadosPessoaisDTO(null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null)));

        assertEquals(Set.of("residesWithBothParents", "userId"),
                fieldPaths(new FormDadosParentesDTO(null, null, null, null, null, null, null, null,
                        null, null, null)));

        assertEquals(Set.of("address", "afterSchoolActivities", "city", "commutingTime",
                        "electricitySource", "hasSewage", "neighborhood", "residenceType",
                        "structureType", "transportType", "waterSupply", "zipCode", "userId"),
                fieldPaths(new FormEnderecoCandidatoDTO(null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null)));

        assertEquals(Set.of("situacaoImovel", "tipoImovel", "estruturaFisica", "esgotoSanitario",
                        "fornecimentoEnergia", "abastecimentoAgua",
                        "CondicoesDeSaudeCasosNaFamilia", "doencasCronicas", "userId"),
                fieldPaths(new CondicoesHabitacionaisDTO(null, null, null, null, null, null,
                        null, null, null)));

        assertEquals(Set.of("userInfoId", "despesasMensais"),
                fieldPaths(new BensPossesCompletoDTO(null, null, null, null, List.of())));
        assertEquals(Set.of("descricao", "valor"),
                fieldPaths(new DespesaMensalDTO(null, " ", null)));

        assertEquals(Set.of("vaiParticipar", "jaFoiContemplado", "userId", "segmentoAno", "serieAno"),
                fieldPaths(new ProcessoDeBolsaDTO(null, null, null, null, null, null, null)));
        assertEquals(Set.of("percentual"),
                fieldPaths(new ProcessoDeBolsaDTO(null, true, true, null, 1L, "Fundamental", "5º ano")));
        assertEquals(Set.of("active"), fieldPaths(new StatusDTO(null)));
        assertTrue(validator.validate(new StatusDTO(false)).isEmpty());

        assertEquals(Set.of("userId", "nomeAluno", "segmentoCursar2025", "nomeResponsavel",
                        "cpfResponsavel", "rendaBrutaFamiliar", "totalComponentesFamilar",
                        "rendaPerCapita", "rendaPerCapitaSalarioMinimo", "percentualLc187",
                        "beneficiarioProgramaRenda", "resideProximoUnidadeEscolar",
                        "candidatoComDeficiencia", "doencaGraveOuDeficienciaFamiliar",
                        "quantidadeMenoresDezoitoAnos"),
                fieldPaths(new ParecerSocioeconomicoDTO(null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null)));

        assertEquals(Set.of("nomeDeclarante", "rgDeclarante", "cpfDeclarante", "nomeAluno",
                        "aceiteTermos", "userId"),
                fieldPaths(new DeclaracaoDTO(null, null, null, null, null, null, null)));

        FormDadosParentesDTO optionalParentFields = new FormDadosParentesDTO(
                null, null, null, null, null, null, null, null, null, "Não", 1L
        );
        assertTrue(validator.validate(optionalParentFields).isEmpty());

        FormEnderecoCandidatoDTO addressWithConditionalFieldsMissing = new FormEnderecoCandidatoDTO(
                null, "Rua A", "Sim", "Recife", "10 min", "COMPANHIA_EXISTENTE",
                "EXISTENTE", "Centro", null, "Casa", "Outros", null,
                "outros", null, "EXISTENTE", null, "50000-000", 1L
        );
        Set<String> conditionalAddressFields = fieldPaths(addressWithConditionalFieldsMissing);
        assertTrue(conditionalAddressFields.containsAll(Set.of(
                "activityDescription", "weeklyFrequency", "transportTypeOthers", "structureTypeOthers"
        )));
    }

    @Test
    void nestedRequestFieldsAreValidatedAndSupportedDatePayloadsDeserialize() throws Exception {
        BensPossesCompletoDTO bens = new BensPossesCompletoDTO(
                1L, null, null, null, List.of(new DespesaMensalDTO(null, " ", null))
        );
        assertEquals(Set.of("despesasMensais[0].descricao", "despesasMensais[0].valor"),
                fieldPaths(bens));

        FormDadosPessoaisDTO personalData = OBJECT_MAPPER.readValue(
                """
                        {
                          "dateBirth": "2000-04-15T00:00:00.000Z"
                        }
                        """,
                FormDadosPessoaisDTO.class
        );
        assertEquals(LocalDate.of(2000, 4, 15), personalData.dataNascimento());

        ComposicaoFamiliarDTO familyMember = OBJECT_MAPPER.readValue(
                """
                        {
                          "dataNascimento": "15/04/2000"
                        }
                        """,
                ComposicaoFamiliarDTO.class
        );
        assertEquals(LocalDate.of(2000, 4, 15), familyMember.dataNascimento());
    }

    private Set<String> fieldPaths(Object dto) {
        return validator.validate(dto).stream()
                .map(violation -> violation.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    @Test
    void mapStructMapsRecordComponentsInBothDirections() {
        ComposicaoFamiliar entity = new ComposicaoFamiliar();
        entity.setId(7L);
        entity.setNomeCompleto("João Silva");
        entity.setEscolaridade("Ensino médio");
        entity.setGrauParentesco("Irmão");
        entity.setDataNascimento(LocalDate.of(2005, 8, 1));

        ComposicaoFamiliarDTO dto = ComposicaoFamiliarMapper.INSTANCE.toDTOList(List.of(entity)).get(0);
        assertEquals(7L, dto.id());
        assertEquals("João Silva", dto.nomeCompleto());
        assertEquals("Ensino médio", dto.escolaridade());
        assertEquals(entity.getDataNascimento(), dto.dataNascimento());

        ComposicaoFamiliar mappedEntity = ComposicaoFamiliarMapper.INSTANCE.toEntityList(List.of(dto)).get(0);
        assertEquals(dto.id(), mappedEntity.getId());
        assertEquals(dto.nomeCompleto(), mappedEntity.getNomeCompleto());
        assertEquals(dto.grauParentesco(), mappedEntity.getGrauParentesco());
    }

    @Test
    void userInfoMapStructMapsRecordComponentsToAndFromEntities() throws Exception {
        Role role = new Role();
        role.setName(RoleEnum.ROLE_USER);

        UserInfo entity = new UserInfo();
        entity.setId(17L);
        entity.setName("Ana Silva");
        entity.setCpf("12345678901");
        entity.setEmail("ana@example.com");
        entity.setRole(role);
        entity.setTipoAluno(TipoAlunoEnum.ESCOLA_PARTICULAR);

        UserInfoMapper mapper = Mappers.getMapper(UserInfoMapper.class);
        UserInfoDTO dto = mapper.toDTO(entity);

        assertEquals(17L, dto.userId());
        assertEquals("Ana Silva", dto.name());
        assertEquals("12345678901", dto.userName());
        assertEquals("ROLE_USER", dto.roleName());
        assertEquals(TipoAlunoEnum.ESCOLA_PARTICULAR, dto.tipoAluno());

        JsonNode userResponse = OBJECT_MAPPER.readTree(OBJECT_MAPPER.writeValueAsString(dto));
        assertEquals(17L, userResponse.get("userId").asLong());
        assertEquals("12345678901", userResponse.get("userName").asText());
        assertTrue(userResponse.has("isActive"));
        assertTrue(userResponse.get("password").isNull());

        UserInfo mappedEntity = mapper.toEntity(dto);
        assertEquals(dto.userId(), mappedEntity.getId());
        assertEquals(dto.cpf(), mappedEntity.getUserName());
        assertEquals(dto.cpf(), mappedEntity.getPassword());
        assertTrue(mappedEntity.isActive());
        assertTrue(mappedEntity.isFirstLogin());
    }
}
