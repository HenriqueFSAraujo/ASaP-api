package pdev.com.agenda.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pdev.com.agenda.domain.entity.Declaracao;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.dto.DeclaracaoDTO;
import pdev.com.agenda.repository.DeclaracaoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeclaracaoServiceMappingTest {

    @Mock
    private DeclaracaoRepository declaracaoRepository;

    @InjectMocks
    private DeclaracaoService declaracaoService;

    @Test
    void saveMapsAllFieldsAndUserAssociationInBothDirections() {
        DeclaracaoDTO request = new DeclaracaoDTO(
                null, "Ana Silva", "RG123", "12345678901", "Aluno Silva", true, 42L
        );
        when(declaracaoRepository.findByUser_Id(42L)).thenReturn(List.of());
        when(declaracaoRepository.save(any(Declaracao.class))).thenAnswer(invocation -> {
            Declaracao saved = invocation.getArgument(0);
            saved.setId(31L);
            return saved;
        });

        DeclaracaoDTO response = declaracaoService.save(request);

        ArgumentCaptor<Declaracao> savedEntity = ArgumentCaptor.forClass(Declaracao.class);
        verify(declaracaoRepository).save(savedEntity.capture());
        Declaracao entity = savedEntity.getValue();
        assertEquals("Ana Silva", entity.getNomeDeclarante());
        assertEquals("RG123", entity.getRgDeclarante());
        assertEquals("12345678901", entity.getCpfDeclarante());
        assertEquals("Aluno Silva", entity.getNomeAluno());
        assertEquals(Boolean.TRUE, entity.getAceiteTermos());
        assertNotNull(entity.getUser());
        assertEquals(42L, entity.getUser().getId());

        assertEquals(31L, response.id());
        assertEquals(request.nomeDeclarante(), response.nomeDeclarante());
        assertEquals(request.rgDeclarante(), response.rgDeclarante());
        assertEquals(request.cpfDeclarante(), response.cpfDeclarante());
        assertEquals(request.nomeAluno(), response.nomeAluno());
        assertEquals(request.aceiteTermos(), response.aceiteTermos());
        assertEquals(request.userId(), response.userId());
    }

    @Test
    void readMapsNullUserAssociationWithoutInventingAnIdentifier() {
        Declaracao entity = new Declaracao();
        entity.setId(5L);
        entity.setNomeDeclarante("Ana Silva");

        when(declaracaoRepository.findById(5L)).thenReturn(Optional.of(entity));

        DeclaracaoDTO response = declaracaoService.findById(5L);

        assertEquals(5L, response.id());
        assertEquals("Ana Silva", response.nomeDeclarante());
        assertNull(response.userId());
    }
}
