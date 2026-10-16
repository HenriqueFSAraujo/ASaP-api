package pdev.com.agenda.domain.mapper;

import org.junit.jupiter.api.Test;
import pdev.com.agenda.domain.dto.FormDadosParentesDTO;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoDTO;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.UserLoginRequest;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.Role;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.enuns.RoleEnum;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class SensitiveMapperContractTest {

    @Test
    void mapsPersonalFormFieldsAndKeepsUserSchoolAndDateValues() {
        FormDadosPessoaisMapper mapper = new FormDadosPessoaisMapper();
        LocalDate birthDate = LocalDate.of(2000, 4, 15);
        FormDadosPessoaisDTO dto = new FormDadosPessoaisDTO(
                4L, "Ana Silva", "ana@example.com", "12345678901", "RG123",
                "Brasileira", "Recife", "Parda", "81999990000", "Feminino",
                "98765432100", birthDate, "Não", "EDU123", "PENDENTE",
                12L, 8L, TipoEscolaEnum.PARTICULAR
        );

        FormDadosPessoais entity = mapper.toEntity(dto);

        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.fullName(), entity.getFullName());
        assertEquals(dto.email(), entity.getEmail());
        assertEquals(dto.cpf(), entity.getCpf());
        assertEquals(birthDate, entity.getDataNascimento());
        assertEquals(dto.userId(), entity.getUser().getId());
        assertEquals(dto.escolaId(), entity.getEscola().getId());

        Escola escola = new Escola();
        escola.setId(dto.escolaId());
        escola.setTipo(dto.tipoEscola());
        entity.setEscola(escola);
        assertEquals(dto, mapper.toDto(entity));
    }

    @Test
    void parentMapperPreservesItsLimitedNonNullPatchBehavior() {
        FormDadosParentesMapper mapper = new FormDadosParentesMapper();
        FormDadosParentes entity = new FormDadosParentes();
        entity.setParent1Cpf("old-cpf");
        entity.setParent1FullName("Nome anterior");
        entity.setParent2Cpf("parent-2-cpf");
        UserInfo currentUser = new UserInfo();
        currentUser.setId(20L);
        entity.setUser(currentUser);

        FormDadosParentesDTO patch = new FormDadosParentesDTO(
                null, "new-cpf", null, null, null, "changed-parent-2",
                null, null, null, null, 99L
        );
        mapper.updateEntityFromDTO(entity, patch);

        assertEquals("new-cpf", entity.getParent1Cpf());
        assertEquals("Nome anterior", entity.getParent1FullName());
        assertEquals("parent-2-cpf", entity.getParent2Cpf());
        assertEquals(20L, entity.getUser().getId());

        FormDadosParentesDTO response = mapper.toDTO(entity);
        assertEquals(20L, response.userId());
        assertEquals("new-cpf", response.parent1Cpf());
    }

    @Test
    void addressMapperPreservesEveryFieldIncludingCommutingTimeAndUserId() {
        FormEnderecoCandidatoMapper mapper = new FormEnderecoCandidatoMapper();
        FormEnderecoCandidatoDTO dto = new FormEnderecoCandidatoDTO(
                "Reforço", "Rua A", "Sim", "Recife", "15 minutos",
                "Rede pública", "Sim", "Centro", "Próximo à praça",
                "Alugada", "Alvenaria", "Outro material", "Ônibus",
                "Van", "Rede pública", "5 dias", "50000-000", 33L
        );

        FormEnderecoCandidato entity = mapper.toEntity(dto);

        assertEquals(dto.activityDescription(), entity.getActivityDescription());
        assertEquals(dto.commutingTime(), entity.getCommutingTime());
        assertEquals(dto.weeklyFrequency(), entity.getWeeklyFrequency());
        assertEquals(dto.zipCode(), entity.getZipCode());
        assertEquals(dto.userId(), entity.getUser().getId());
        assertEquals(dto, mapper.toDto(entity));
    }

    @Test
    void scholarshipProcessMapperKeepsRequestAndResponseMappingSemantics() {
        ProcessoDeBolsaMapper mapper = new ProcessoDeBolsaMapper();
        ProcessoDeBolsaDTO request = new ProcessoDeBolsaDTO(
                18L, true, false, "50%", 7L, "Fundamental", "5º ano"
        );

        ProcessoDeBolsa entity = mapper.toEntity(request);

        assertNull(entity.getId());
        assertEquals(request.vaiParticipar(), entity.isVaiParticipar());
        assertEquals(request.jaFoiContemplado(), entity.isJaFoiContemplado());
        assertEquals(request.percentual(), entity.getPercentual());
        assertEquals(request.userId(), entity.getUser().getId());
        assertEquals(request.segmentoAno(), entity.getSegmentoAno());
        assertEquals(request.serieAno(), entity.getSerieAno());

        entity.setId(18L);
        assertEquals(request, mapper.toResponse(entity));
    }

    @Test
    void socioeconomicOpinionMapperPreservesDatesAndPartialUpdateBehavior() {
        ParecerSocioeconomicoMapper mapper = new ParecerSocioeconomicoMapper();
        LocalDate birthDate = LocalDate.of(2005, 2, 3);
        LocalDate finalizedDate = LocalDate.of(2026, 10, 15);
        UserInfo user = new UserInfo();
        user.setId(44L);
        ParecerSocioeconomico entity = new ParecerSocioeconomico();
        entity.setUser(user);
        entity.setNomeAluno("Aluno anterior");
        entity.setDataNascimentoAluno(birthDate);
        entity.setDataFinalizacaoParecer(finalizedDate);
        entity.setRendaBrutaFamiliar(new BigDecimal("2500.00"));

        ParecerSocioeconomicoDTO response = mapper.toDTO(entity);
        assertEquals(44L, response.userId());
        assertEquals(birthDate, response.dataNascimentoAluno());
        assertEquals(finalizedDate, response.dataFinalizacaoParecer());
        assertEquals(new BigDecimal("2500.00"), response.rendaBrutaFamiliar());

        ParecerSocioeconomicoDTO patch = ParecerSocioeconomicoDTO.builder()
                .nomeAluno("Aluno atualizado")
                .build();
        mapper.updateEntityFromDTO(entity, patch);

        assertEquals("Aluno atualizado", entity.getNomeAluno());
        assertSame(user, entity.getUser());
        assertEquals(birthDate, entity.getDataNascimentoAluno());
        assertEquals(finalizedDate, entity.getDataFinalizacaoParecer());
        assertEquals(new BigDecimal("2500.00"), entity.getRendaBrutaFamiliar());
    }

    @Test
    void loginMapperTransfersCredentialValuesWithoutTransformingThem() {
        UserLoginMapper mapper = new UserLoginMapper();
        UserLoginRequest request = new UserLoginRequest("user-test", "literal-password");

        UserInfo entity = mapper.toEntity(request);

        assertEquals(request.userName(), entity.getUserName());
        assertEquals(request.password(), entity.getPassword());

        Role role = new Role();
        role.setName(RoleEnum.ROLE_USER);
        entity.setRole(role);
        UserLoginResponse response = mapper.toDto(entity);
        assertEquals(request.userName(), response.userName());
        assertEquals(RoleEnum.ROLE_USER, response.role());
        assertSame(entity, response.userInfo());
    }
}
