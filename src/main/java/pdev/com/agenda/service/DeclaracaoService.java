package pdev.com.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.Declaracao;
import pdev.com.agenda.dto.DeclaracaoDTO;
import pdev.com.agenda.repository.DeclaracaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeclaracaoService {

    @Autowired
    private DeclaracaoRepository declaracaoRepository;

    public List<DeclaracaoDTO> findAll() {
        return declaracaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DeclaracaoDTO findById(Long id) {
        Optional<Declaracao> declaracao = declaracaoRepository.findById(id);
        return declaracao.map(this::convertToDTO).orElse(null);
    }

    public DeclaracaoDTO save(DeclaracaoDTO declaracaoDTO) {
        Declaracao declaracao = convertToEntity(declaracaoDTO);
        Declaracao savedDeclaracao = declaracaoRepository.save(declaracao);
        return convertToDTO(savedDeclaracao);
    }

    public void deleteById(Long id) {
        declaracaoRepository.deleteById(id);
    }

    private DeclaracaoDTO convertToDTO(Declaracao declaracao) {
        DeclaracaoDTO dto = new DeclaracaoDTO();
        dto.setId(declaracao.getId());
        dto.setNomeDeclarante(declaracao.getNomeDeclarante());
        dto.setRgDeclarante(declaracao.getRgDeclarante());
        dto.setCpfDeclarante(declaracao.getCpfDeclarante());
        dto.setNomeAluno(declaracao.getNomeAluno());
        dto.setAceiteTermos(declaracao.getAceiteTermos());
        return dto;
    }

    private Declaracao convertToEntity(DeclaracaoDTO dto) {
        Declaracao declaracao = new Declaracao();
        declaracao.setId(dto.getId());
        declaracao.setNomeDeclarante(dto.getNomeDeclarante());
        declaracao.setRgDeclarante(dto.getRgDeclarante());
        declaracao.setCpfDeclarante(dto.getCpfDeclarante());
        declaracao.setNomeAluno(dto.getNomeAluno());
        declaracao.setAceiteTermos(dto.getAceiteTermos());
        return declaracao;
    }
}
