package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoRequest;
import pdev.com.agenda.domain.dto.ParecerSocioeconomicoResponse;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;

    @Component
    public class ParecerSocioeconomicoMapper {

        public ParecerSocioeconomico toEntity(ParecerSocioeconomicoRequest dto) {
            if (dto == null) return null;

            ParecerSocioeconomico entity = new ParecerSocioeconomico();
            entity.setNomeAluno(dto.getNomeAluno());
            entity.setDataNascimentoAluno(dto.getDataNascimentoAluno());
            entity.setSegmentoCursar2025(dto.getSegmentoCursar2025());
            entity.setNomeResponsavel(dto.getNomeResponsavel());
            entity.setCpfResponsavel(dto.getCpfResponsavel());
            entity.setTelefoneResponsavel(dto.getTelefoneResponsavel());
            entity.setRendaBrutaFamiliar(dto.getRendaBrutaFamiliar());
            entity.setQuantidadePessoasFamilia(dto.getQuantidadePessoasFamilia());
            entity.setRendaPerCapita(dto.getRendaPerCapita());
            entity.setRendaPerCapitaSalarioMinimo(dto.getRendaPerCapitaSalarioMinimo());
            entity.setPercentualLc187(dto.getPercentualLc187());
            entity.setBeneficiarioProgramaRenda(dto.getBeneficiarioProgramaRenda());
            entity.setResideProximoUnidadeEscolar(dto.getResideProximoUnidadeEscolar());
            entity.setCandidatoComDeficiencia(dto.getCandidatoComDeficiencia());
            entity.setDoencaGraveOuDeficienciaFamiliar(dto.getDoencaGraveOuDeficienciaFamiliar());
            entity.setQuantidadeMenoresDezoitoAnos(dto.getQuantidadeMenoresDezoitoAnos());
            entity.setAspectosRelevantes(dto.getAspectosRelevantes());
            entity.setResultadoSocioeconomico(dto.getResultadoSocioeconomico());
            entity.setDataFinalizacaoParecer(dto.getDataFinalizacaoParecer());
            return entity;
        }

        public ParecerSocioeconomicoResponse toDTO(ParecerSocioeconomico entity) {
            if (entity == null) return null;

            ParecerSocioeconomicoResponse dto = new ParecerSocioeconomicoResponse();
            dto.setNomeAluno(entity.getNomeAluno());
            dto.setDataNascimentoAluno(entity.getDataNascimentoAluno());
            dto.setSegmentoCursar2025(entity.getSegmentoCursar2025());
            dto.setNomeResponsavel(entity.getNomeResponsavel());
            dto.setCpfResponsavel(entity.getCpfResponsavel());
            dto.setTelefoneResponsavel(entity.getTelefoneResponsavel());
            dto.setRendaBrutaFamiliar(entity.getRendaBrutaFamiliar());
            dto.setQuantidadePessoasFamilia(entity.getQuantidadePessoasFamilia());
            dto.setRendaPerCapita(entity.getRendaPerCapita());
            dto.setRendaPerCapitaSalarioMinimo(entity.getRendaPerCapitaSalarioMinimo());
            dto.setPercentualLc187(entity.getPercentualLc187());
            dto.setBeneficiarioProgramaRenda(entity.getBeneficiarioProgramaRenda());
            dto.setResideProximoUnidadeEscolar(entity.getResideProximoUnidadeEscolar());
            dto.setCandidatoComDeficiencia(entity.getCandidatoComDeficiencia());
            dto.setDoencaGraveOuDeficienciaFamiliar(entity.getDoencaGraveOuDeficienciaFamiliar());
            dto.setQuantidadeMenoresDezoitoAnos(entity.getQuantidadeMenoresDezoitoAnos());
            dto.setAspectosRelevantes(entity.getAspectosRelevantes());
            dto.setResultadoSocioeconomico(entity.getResultadoSocioeconomico());
            dto.setDataFinalizacaoParecer(entity.getDataFinalizacaoParecer());
            return dto;
        }

        public void updateEntityFromDTO(ParecerSocioeconomico entity, ParecerSocioeconomicoRequest dto) {
            if (entity == null || dto == null) return;

            if (dto.getNomeAluno() != null) entity.setNomeAluno(dto.getNomeAluno());
            if (dto.getDataNascimentoAluno() != null) entity.setDataNascimentoAluno(dto.getDataNascimentoAluno());
            if (dto.getSegmentoCursar2025() != null) entity.setSegmentoCursar2025(dto.getSegmentoCursar2025());
            if (dto.getNomeResponsavel() != null) entity.setNomeResponsavel(dto.getNomeResponsavel());
            if (dto.getCpfResponsavel() != null) entity.setCpfResponsavel(dto.getCpfResponsavel());
            if (dto.getTelefoneResponsavel() != null) entity.setTelefoneResponsavel(dto.getTelefoneResponsavel());
            if (dto.getRendaBrutaFamiliar() != null) entity.setRendaBrutaFamiliar(dto.getRendaBrutaFamiliar());
            if (dto.getQuantidadePessoasFamilia() != null) entity.setQuantidadePessoasFamilia(dto.getQuantidadePessoasFamilia());
            if (dto.getRendaPerCapita() != null) entity.setRendaPerCapita(dto.getRendaPerCapita());
            if (dto.getRendaPerCapitaSalarioMinimo() != null) entity.setRendaPerCapitaSalarioMinimo(dto.getRendaPerCapitaSalarioMinimo());
            if (dto.getPercentualLc187() != null) entity.setPercentualLc187(dto.getPercentualLc187());
            if (dto.getBeneficiarioProgramaRenda() != null) entity.setBeneficiarioProgramaRenda(dto.getBeneficiarioProgramaRenda());
            if (dto.getResideProximoUnidadeEscolar() != null) entity.setResideProximoUnidadeEscolar(dto.getResideProximoUnidadeEscolar());
            if (dto.getCandidatoComDeficiencia() != null) entity.setCandidatoComDeficiencia(dto.getCandidatoComDeficiencia());
            if (dto.getDoencaGraveOuDeficienciaFamiliar() != null) entity.setDoencaGraveOuDeficienciaFamiliar(dto.getDoencaGraveOuDeficienciaFamiliar());
            if (dto.getQuantidadeMenoresDezoitoAnos() != null) entity.setQuantidadeMenoresDezoitoAnos(dto.getQuantidadeMenoresDezoitoAnos());
            if (dto.getAspectosRelevantes() != null) entity.setAspectosRelevantes(dto.getAspectosRelevantes());
            if (dto.getResultadoSocioeconomico() != null) entity.setResultadoSocioeconomico(dto.getResultadoSocioeconomico());
            if (dto.getDataFinalizacaoParecer() != null) entity.setDataFinalizacaoParecer(dto.getDataFinalizacaoParecer());
        }
    }