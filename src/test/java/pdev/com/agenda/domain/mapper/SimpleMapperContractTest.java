package pdev.com.agenda.domain.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pdev.com.agenda.domain.dto.DespesaMensalDTO;
import pdev.com.agenda.domain.dto.FamiliarEscolaParticularDTO;
import pdev.com.agenda.domain.dto.PessoaComDeficienciaDTO;
import pdev.com.agenda.domain.dto.VeiculoDTO;
import pdev.com.agenda.domain.entity.DespesaMensal;
import pdev.com.agenda.domain.entity.FamiliarEscolaParticular;
import pdev.com.agenda.domain.entity.PessoaComDeficiencia;
import pdev.com.agenda.domain.entity.Veiculo;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SimpleMapperContractTest {

    @Test
    void mapsDespesaMensalFieldsAndLeavesAggregateOwnedFieldsUntouched() {
        DespesaMensalMapper mapper = Mappers.getMapper(DespesaMensalMapper.class);
        DespesaMensalDTO dto = new DespesaMensalDTO(3L, "Alimentação", new BigDecimal("125.50"));

        DespesaMensal entity = mapper.toEntity(dto);

        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.descricao(), entity.getDescricao());
        assertEquals(dto.valor(), entity.getValor());
        assertNull(entity.getBensPosses());
        assertNull(entity.getStatus());
        assertEquals(dto, mapper.toDTO(entity));
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDTO(null));
    }

    @Test
    void mapsFamiliarEscolaFieldsAndLeavesAggregateOwnedFieldsUntouched() {
        FamiliarEscolaParticularMapper mapper = Mappers.getMapper(FamiliarEscolaParticularMapper.class);
        FamiliarEscolaParticularDTO dto = new FamiliarEscolaParticularDTO(
                4L, "Maria", "Escola A", new BigDecimal("875.00"));

        FamiliarEscolaParticular entity = mapper.toEntity(dto);

        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.nome(), entity.getNome());
        assertEquals(dto.escola(), entity.getEscola());
        assertEquals(dto.valorMensal(), entity.getValorMensal());
        assertNull(entity.getBensPosses());
        assertNull(entity.getStatus());
        assertEquals(dto, mapper.toDTO(entity));
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDTO(null));
    }

    @Test
    void mapsPessoaComDeficienciaFieldsAndLeavesAggregateOwnedFieldsUntouched() {
        PessoaComDeficienciaMapper mapper = Mappers.getMapper(PessoaComDeficienciaMapper.class);
        PessoaComDeficienciaDTO dto = new PessoaComDeficienciaDTO(
                5L, "João", "Auditiva", new BigDecimal("90.00"));

        PessoaComDeficiencia entity = mapper.toEntity(dto);

        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.nome(), entity.getNome());
        assertEquals(dto.tipoDeficiencia(), entity.getTipoDeficiencia());
        assertEquals(dto.despesaMensal(), entity.getDespesaMensal());
        assertNull(entity.getBensPosses());
        assertNull(entity.getStatus());
        assertEquals(dto, mapper.toDTO(entity));
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDTO(null));
    }

    @Test
    void mapsVeiculoFieldsAndLeavesAggregateOwnedFieldsUntouched() {
        VeiculoMapper mapper = Mappers.getMapper(VeiculoMapper.class);
        VeiculoDTO dto = new VeiculoDTO(6L, "Modelo A", 2022, "Trabalho");

        Veiculo entity = mapper.toEntity(dto);

        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.marcaModelo(), entity.getMarcaModelo());
        assertEquals(dto.anoFabricacao(), entity.getAnoFabricacao());
        assertEquals(dto.utilizacao(), entity.getUtilizacao());
        assertNull(entity.getBensPosses());
        assertNull(entity.getStatus());
        assertEquals(dto, mapper.toDTO(entity));
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDTO(null));
    }
}
