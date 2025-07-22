package pdev.com.agenda.util;

import pdev.com.agenda.domain.entity.ParecerSocioeconomico;

import java.util.HashMap;
import java.util.Map;

public class PdfUtil {

    public static Map<String, String> getDadosMap(ParecerSocioeconomico p) {
        Map<String, String> map = new HashMap<>();
        map.put("nomeAluno", p.getNomeAluno());
        map.put("dataNascimentoAluno", String.valueOf(p.getDataNascimentoAluno()));
        map.put("segmentoCursar2025", p.getSegmentoCursar2025());
        map.put("nomeResponsavel", p.getNomeResponsavel());
        map.put("cpfResponsavel", p.getCpfResponsavel());
        map.put("telefoneResponsavel", p.getTelefoneResponsavel());
        map.put("rendaBrutaFamiliar", String.valueOf(p.getRendaBrutaFamiliar()));
        map.put("quantidadePessoasFamilia", String.valueOf(p.getQuantidadePessoasFamilia()));
        map.put("rendaPerCapita", String.valueOf(p.getRendaPerCapita()));
        map.put("rendaPerCapitaSalarioMinimo", String.valueOf(p.getRendaPerCapitaSalarioMinimo()));
        map.put("percentualLc187", String.valueOf(p.getPercentualLc187()));
        map.put("beneficiarioProgramaRenda", booleanToSimNao(p.getBeneficiarioProgramaRenda()));
        map.put("resideProximoUnidadeEscolar", booleanToSimNao(p.getResideProximoUnidadeEscolar()));
        map.put("candidatoComDeficiencia", booleanToSimNao(p.getCandidatoComDeficiencia()));
        map.put("doencaGraveOuDeficienciaFamiliar", booleanToSimNao(p.getDoencaGraveOuDeficienciaFamiliar()));
        map.put("quantidadeMenoresDezoitoAnos", String.valueOf(p.getQuantidadeMenoresDezoitoAnos()));
        map.put("aspectosRelevantes", p.getAspectosRelevantes());
        map.put("resultadoSocioeconomico", p.getResultadoSocioeconomico());
        map.put("dataFinalizacaoParecer", String.valueOf(p.getDataFinalizacaoParecer()));
        return map;
    }

    private static String booleanToSimNao(Boolean value) {
        return value == null ? "N/A" : (value ? "Sim" : "Não");
    }
}