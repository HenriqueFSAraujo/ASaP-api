package pdev.com.agenda.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record BensPossesCompletoDTO(
        @NotNull(message = "O usuário é obrigatório.")
        Long userInfoId,
        List<VeiculoDTO> veiculos,
        List<FamiliarEscolaParticularDTO> familiaresEscola,
        List<PessoaComDeficienciaDTO> pessoasComDeficiencia,
        @NotEmpty(message = "Informe ao menos uma despesa mensal.")
        List<@Valid DespesaMensalDTO> despesasMensais
) {
}
