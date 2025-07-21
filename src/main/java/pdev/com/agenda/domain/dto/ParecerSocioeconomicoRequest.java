package pdev.com.agenda.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ParecerSocioeconomicoRequest {

    private Long userId;
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
