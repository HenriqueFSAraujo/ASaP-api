package pdev.com.agenda.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pdev.com.agenda.domain.dto.FormDadosPessoaisDTO;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;
import pdev.com.agenda.domain.mapper.FormDadosPessoaisMapper;
import pdev.com.agenda.domain.repository.EscolaRepository;
import pdev.com.agenda.domain.repository.FormRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormServiceMappingTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private EscolaRepository escolaRepository;

    private FormService formService;

    @BeforeEach
    void setUp() {
        formService = new FormService(formRepository, new FormDadosPessoaisMapper(), escolaRepository);
    }

    @Test
    void resolvesSchoolByIdAndReturnsItsTypeForFrontendPayload() {
        Escola school = new Escola();
        school.setId(8L);
        school.setTipo(TipoEscolaEnum.PARTICULAR);

        when(formRepository.findByUserId(12L)).thenReturn(Optional.empty());
        when(escolaRepository.findById(8L)).thenReturn(Optional.of(school));
        when(formRepository.save(any(FormDadosPessoais.class))).thenAnswer(invocation -> {
            FormDadosPessoais saved = invocation.getArgument(0);
            saved.setId(3L);
            return saved;
        });

        FormDadosPessoaisDTO frontendRequest = new FormDadosPessoaisDTO(
                null, "Ana Silva", "", "12345678901", "RG123",
                "Brasileira", "Recife", "Parda", "81999990000", "Feminino",
                "", LocalDate.of(2000, 4, 15), "Não", "EDU123", null,
                12L, 8L, null
        );

        FormDadosPessoaisDTO response = formService.createDadosPessoais(frontendRequest);

        ArgumentCaptor<FormDadosPessoais> savedEntity = ArgumentCaptor.forClass(FormDadosPessoais.class);
        verify(formRepository).save(savedEntity.capture());
        assertEquals(8L, savedEntity.getValue().getEscola().getId());
        assertEquals(12L, savedEntity.getValue().getUser().getId());
        assertEquals(frontendRequest.email(), savedEntity.getValue().getEmail());
        assertEquals(frontendRequest.dataNascimento(), savedEntity.getValue().getDataNascimento());

        verify(escolaRepository).findById(8L);
        assertEquals(3L, response.id());
        assertEquals(TipoEscolaEnum.PARTICULAR, response.tipoEscola());
        assertEquals(frontendRequest.nacionalidade(), response.nacionalidade());
        assertEquals(frontendRequest.naturalidade(), response.naturalidade());
        assertEquals(frontendRequest.cor(), response.cor());
        assertEquals(frontendRequest.dataNascimento(), response.dataNascimento());
        assertNull(frontendRequest.tipoEscola());
    }
}
