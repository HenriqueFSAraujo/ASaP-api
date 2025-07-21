package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParecerSocioeconomicoResponse {

    private Long id;
    private String nomeAluno;
    private LocalDate dataNascimentoAluno;
    private String segmentoCursar2025;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefoneResponsavel;
    private BigDecimal rendaBrutaFamiliar;
    private Integer quantidadePessoasFamilia;
    private BigDecimal rendaPerCapita;
    private BigDecimal rendaPerCapitaSalarioMinimo;
    private Double percentualLc187;
    private Boolean beneficiarioProgramaRenda;
    private Boolean resideProximoUnidadeEscolar;
    private Boolean candidatoComDeficiencia;
    private Boolean doencaGraveOuDeficienciaFamiliar;
    private Integer quantidadeMenoresDezoitoAnos;
    private String aspectosRelevantes;
    private String resultadoSocioeconomico;
    private LocalDate dataFinalizacaoParecer;
}