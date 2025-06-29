package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BensPossesCompletoDTO {

    private Long userInfoId;

    private List<VeiculoDTO> veiculos;
    private List<FamiliarEscolaParticularDTO> familiaresEscola;
    private List<PessoaComDeficienciaDTO> pessoasComDeficiencia;
    private List<DespesaMensalDTO> despesasMensais;
}
