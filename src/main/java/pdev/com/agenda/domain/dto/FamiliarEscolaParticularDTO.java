package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamiliarEscolaParticularDTO {

    private Long id;
    private String nome;
    private String escola;
    private BigDecimal valorMensal;
}
