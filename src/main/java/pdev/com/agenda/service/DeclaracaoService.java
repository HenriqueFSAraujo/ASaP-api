package pdev.com.agenda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.entity.Declaracao;
import pdev.com.agenda.dto.DeclaracaoDTO;
import pdev.com.agenda.repository.DeclaracaoRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeclaracaoService {

    private final DeclaracaoRepository declaracaoRepository;

    public List<DeclaracaoDTO> findAll() {
        return declaracaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DeclaracaoDTO findById(Long id) {
        Optional<Declaracao> declaracao = declaracaoRepository.findById(id);
        return declaracao.map(this::convertToDTO).orElse(null);
    }

    /**
     * Upsert por user_id: cada usuário tem no máximo uma declaração (consent terms).
     * <p>
     * Antes desta correção, cada submit criava um registro novo — acumulando declarações
     * duplicadas para o mesmo usuário. Esta versão:
     * <ul>
     *   <li>Se existir declaração(ões) do usuário, atualiza a mais recente (id DESC)
     *       e remove as duplicadas mais antigas (self-healing).</li>
     *   <li>Se não existir, cria uma nova.</li>
     * </ul>
     * Quando {@code userId} for nulo (uso administrativo sem vínculo), mantém o comportamento
     * antigo de sempre criar nova.
     */
    @Transactional
    public DeclaracaoDTO save(DeclaracaoDTO declaracaoDTO) {
        if (declaracaoDTO.userId() == null) {
            // Sem vínculo a usuário — comportamento antigo (sem upsert).
            Declaracao novo = convertToEntity(declaracaoDTO);
            return convertToDTO(declaracaoRepository.save(novo));
        }

        List<Declaracao> existentes = declaracaoRepository.findByUser_Id(declaracaoDTO.userId())
                .stream()
                .sorted(Comparator.comparingLong(Declaracao::getId).reversed())
                .collect(Collectors.toList());

        Declaracao canonica;
        if (existentes.isEmpty()) {
            canonica = convertToEntity(declaracaoDTO);
        } else {
            canonica = existentes.get(0);
            applyDtoToEntity(declaracaoDTO, canonica);
            if (existentes.size() > 1) {
                declaracaoRepository.deleteAll(existentes.subList(1, existentes.size()));
            }
        }

        return convertToDTO(declaracaoRepository.save(canonica));
    }

    public void deleteById(Long id) {
        declaracaoRepository.deleteById(id);
    }

    public List<DeclaracaoDTO> findByUserId(Long userId) {
        return declaracaoRepository.findByUser_Id(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /** Aplica os campos do DTO sobre uma entidade já existente (usado no upsert). */
    private void applyDtoToEntity(DeclaracaoDTO dto, Declaracao entity) {
        entity.setNomeDeclarante(dto.nomeDeclarante());
        entity.setRgDeclarante(dto.rgDeclarante());
        entity.setCpfDeclarante(dto.cpfDeclarante());
        entity.setNomeAluno(dto.nomeAluno());
        entity.setAceiteTermos(dto.aceiteTermos());
    }

    private DeclaracaoDTO convertToDTO(Declaracao declaracao) {
        return new DeclaracaoDTO(
                declaracao.getId(),
                declaracao.getNomeDeclarante(),
                declaracao.getRgDeclarante(),
                declaracao.getCpfDeclarante(),
                declaracao.getNomeAluno(),
                declaracao.getAceiteTermos(),
                declaracao.getUser() != null ? declaracao.getUser().getId() : null
        );
    }

    private Declaracao convertToEntity(DeclaracaoDTO dto) {
        Declaracao declaracao = new Declaracao();
        declaracao.setId(dto.id());
        declaracao.setNomeDeclarante(dto.nomeDeclarante());
        declaracao.setRgDeclarante(dto.rgDeclarante());
        declaracao.setCpfDeclarante(dto.cpfDeclarante());
        declaracao.setNomeAluno(dto.nomeAluno());
        declaracao.setAceiteTermos(dto.aceiteTermos());
        if (dto.userId() != null) {
            pdev.com.agenda.domain.entity.UserInfo user = new pdev.com.agenda.domain.entity.UserInfo();
            user.setId(dto.userId());
            declaracao.setUser(user);
        }
        return declaracao;
    }
}
