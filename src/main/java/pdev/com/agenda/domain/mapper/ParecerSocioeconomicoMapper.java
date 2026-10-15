package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoDTO;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
public class ParecerSocioeconomicoMapper {

    public ParecerSocioeconomico toEntity(ParecerSocioeconomicoDTO dto) {
        if (dto == null) return null;

        ParecerSocioeconomico entity = new ParecerSocioeconomico();

        if (dto.userId() != null) {
            UserInfo user = new UserInfo();
            user.setId(dto.userId());
            entity.setUser(user);
        }

        entity.setNomeAluno(dto.nomeAluno());
        entity.setDataNascimentoAluno(dto.dataNascimentoAluno());
        entity.setSegmentoCursar2025(dto.segmentoCursar2025());
        entity.setNomeResponsavel(dto.nomeResponsavel());
        entity.setCpfResponsavel(dto.cpfResponsavel());
        entity.setTelefoneResponsavel(dto.telefoneResponsavel());
        entity.setRendaBrutaFamiliar(dto.rendaBrutaFamiliar());
        entity.setTotalComponentesFamilar(dto.totalComponentesFamilar());
        entity.setRendaPerCapita(dto.rendaPerCapita());
        entity.setRendaPerCapitaSalarioMinimo(dto.rendaPerCapitaSalarioMinimo());
        entity.setPercentualLc187(dto.percentualLc187());
        entity.setBeneficiarioProgramaRenda(dto.beneficiarioProgramaRenda());
        entity.setResideProximoUnidadeEscolar(dto.resideProximoUnidadeEscolar());
        entity.setCandidatoComDeficiencia(dto.candidatoComDeficiencia());
        entity.setDoencaGraveOuDeficienciaFamiliar(dto.doencaGraveOuDeficienciaFamiliar());
        entity.setQuantidadeMenoresDezoitoAnos(dto.quantidadeMenoresDezoitoAnos());
        entity.setAspectosRelevantes(dto.aspectosRelevantes());
        entity.setResultadoSocioeconomico(dto.resultadoSocioeconomico());
        entity.setDataFinalizacaoParecer(dto.dataFinalizacaoParecer());

        return entity;
    }

    public ParecerSocioeconomicoDTO toDTO(ParecerSocioeconomico entity) {
        if (entity == null) return null;

        return ParecerSocioeconomicoDTO.builder()
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .nomeAluno(entity.getNomeAluno())
                .dataNascimentoAluno(entity.getDataNascimentoAluno())
                .segmentoCursar2025(entity.getSegmentoCursar2025())
                .nomeResponsavel(entity.getNomeResponsavel())
                .cpfResponsavel(entity.getCpfResponsavel())
                .telefoneResponsavel(entity.getTelefoneResponsavel())
                .rendaBrutaFamiliar(entity.getRendaBrutaFamiliar())
                .totalComponentesFamilar(entity.getTotalComponentesFamilar())
                .rendaPerCapita(entity.getRendaPerCapita())
                .rendaPerCapitaSalarioMinimo(entity.getRendaPerCapitaSalarioMinimo())
                .percentualLc187(entity.getPercentualLc187())
                .beneficiarioProgramaRenda(entity.getBeneficiarioProgramaRenda())
                .resideProximoUnidadeEscolar(entity.getResideProximoUnidadeEscolar())
                .candidatoComDeficiencia(entity.getCandidatoComDeficiencia())
                .doencaGraveOuDeficienciaFamiliar(entity.getDoencaGraveOuDeficienciaFamiliar())
                .quantidadeMenoresDezoitoAnos(entity.getQuantidadeMenoresDezoitoAnos())
                .aspectosRelevantes(entity.getAspectosRelevantes())
                .resultadoSocioeconomico(entity.getResultadoSocioeconomico())
                .dataFinalizacaoParecer(entity.getDataFinalizacaoParecer())
                .build();
    }

    public void updateEntityFromDTO(ParecerSocioeconomico entity, ParecerSocioeconomicoDTO dto) {
        if (entity == null || dto == null) return;

        if (dto.userId() != null) {
            UserInfo user = new UserInfo();
            user.setId(dto.userId());
            entity.setUser(user);
        }

        if (dto.nomeAluno() != null) entity.setNomeAluno(dto.nomeAluno());
        if (dto.dataNascimentoAluno() != null) entity.setDataNascimentoAluno(dto.dataNascimentoAluno());
        if (dto.segmentoCursar2025() != null) entity.setSegmentoCursar2025(dto.segmentoCursar2025());
        if (dto.nomeResponsavel() != null) entity.setNomeResponsavel(dto.nomeResponsavel());
        if (dto.cpfResponsavel() != null) entity.setCpfResponsavel(dto.cpfResponsavel());
        if (dto.telefoneResponsavel() != null) entity.setTelefoneResponsavel(dto.telefoneResponsavel());
        if (dto.rendaBrutaFamiliar() != null) entity.setRendaBrutaFamiliar(dto.rendaBrutaFamiliar());
        if (dto.totalComponentesFamilar() != null) entity.setTotalComponentesFamilar(dto.totalComponentesFamilar());
        if (dto.rendaPerCapita() != null) entity.setRendaPerCapita(dto.rendaPerCapita());
        if (dto.rendaPerCapitaSalarioMinimo() != null)
            entity.setRendaPerCapitaSalarioMinimo(dto.rendaPerCapitaSalarioMinimo());
        if (dto.percentualLc187() != null) entity.setPercentualLc187(dto.percentualLc187());
        if (dto.beneficiarioProgramaRenda() != null)
            entity.setBeneficiarioProgramaRenda(dto.beneficiarioProgramaRenda());
        if (dto.resideProximoUnidadeEscolar() != null)
            entity.setResideProximoUnidadeEscolar(dto.resideProximoUnidadeEscolar());
        if (dto.candidatoComDeficiencia() != null)
            entity.setCandidatoComDeficiencia(dto.candidatoComDeficiencia());
        if (dto.doencaGraveOuDeficienciaFamiliar() != null)
            entity.setDoencaGraveOuDeficienciaFamiliar(dto.doencaGraveOuDeficienciaFamiliar());
        if (dto.quantidadeMenoresDezoitoAnos() != null)
            entity.setQuantidadeMenoresDezoitoAnos(dto.quantidadeMenoresDezoitoAnos());
        if (dto.aspectosRelevantes() != null) entity.setAspectosRelevantes(dto.aspectosRelevantes());
        if (dto.resultadoSocioeconomico() != null) entity.setResultadoSocioeconomico(dto.resultadoSocioeconomico());
        if (dto.dataFinalizacaoParecer() != null) entity.setDataFinalizacaoParecer(dto.dataFinalizacaoParecer());
    }
}
