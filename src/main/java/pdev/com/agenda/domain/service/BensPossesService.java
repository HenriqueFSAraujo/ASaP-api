package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pdev.com.agenda.domain.dto.BensPossesCompletoDTO;
import pdev.com.agenda.domain.entity.BensPosses;
import pdev.com.agenda.domain.entity.DespesaMensal;
import pdev.com.agenda.domain.entity.FamiliarEscolaParticular;
import pdev.com.agenda.domain.entity.PessoaComDeficiencia;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.entity.Veiculo;
import pdev.com.agenda.domain.mapper.DespesaMensalMapper;
import pdev.com.agenda.domain.mapper.FamiliarEscolaParticularMapper;
import pdev.com.agenda.domain.mapper.PessoaComDeficienciaMapper;
import pdev.com.agenda.domain.mapper.VeiculoMapper;
import pdev.com.agenda.domain.repository.BensPossesRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BensPossesService {

    private static final String DEFAULT_STATUS = "PENDENTE";

    private final BensPossesRepository bensPossesRepository;
    private final UserInfoRepository userInfoRepository;

    private final VeiculoMapper veiculoMapper;
    private final FamiliarEscolaParticularMapper familiarMapper;
    private final PessoaComDeficienciaMapper deficienciaMapper;
    private final DespesaMensalMapper despesaMapper;


    /**
     * Salva (upsert) os bens/posses do usuário junto com veículos, familiares em escola particular,
     * pessoas com deficiência e despesas mensais.
     * <p>
     * Comportamento de upsert: se já existir um {@code BensPosses} para o usuário, suas coleções
     * são substituídas (graças a {@code orphanRemoval=true} nas anotações @OneToMany da entidade,
     * limpar a coleção remove os registros antigos do banco). Caso não exista, cria um novo.
     * <p>
     * Antes desta correção, cada chamada criava um novo {@code BensPosses} — múltiplos saves do
     * mesmo usuário resultavam em registros duplicados.
     */
    @Transactional
    public BensPosses salvarCompleto(BensPossesCompletoDTO dto) {
        UserInfo user = userInfoRepository.findById(dto.getUserInfoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado com ID: " + dto.getUserInfoId()));

        // Lista ordenada por id DESC para auto-curar duplicados criados pelo bug antigo:
        // - mantém o registro mais recente como canônico (já com children carregados via EntityGraph)
        // - apaga os duplicados mais antigos
        // - se não houver nenhum, inicializa um novo
        List<BensPosses> existentes = bensPossesRepository
                .findAllWithItensByUserInfoIdOrderByIdDesc(user.getId());

        BensPosses bens;
        if (existentes.isEmpty()) {
            bens = initialBensPosses(user);
        } else {
            bens = existentes.get(0);
            if (existentes.size() > 1) {
                bensPossesRepository.deleteAll(existentes.subList(1, existentes.size()));
            }
        }

        replaceVeiculos(bens, dto);
        replaceFamiliares(bens, dto);
        replacePessoasComDeficiencia(bens, dto);
        replaceDespesas(bens, dto);

        bens.setStatus(DEFAULT_STATUS);
        return bensPossesRepository.save(bens);
    }

    @Transactional(readOnly = true)
    public BensPosses buscarPorUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId não pode ser nulo");
        }
        // Usa a versão "tolerante a duplicados" para o caso de existirem registros
        // antigos criados pelo bug pré-Sprint 2. Retorna o mais recente (id DESC).
        return bensPossesRepository.findAllWithItensByUserInfoIdOrderByIdDesc(userId).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Bens/Posse não encontrado para o usuário: " + userId));
    }

    // ============================== helpers ==============================

    private BensPosses initialBensPosses(UserInfo user) {
        BensPosses novo = new BensPosses();
        novo.setUserInfo(user);
        novo.setVeiculos(new HashSet<>());
        novo.setFamiliaresEscola(new HashSet<>());
        novo.setPessoasComDeficiencia(new HashSet<>());
        novo.setDespesasMensais(new HashSet<>());
        return novo;
    }

    private void replaceVeiculos(BensPosses bens, BensPossesCompletoDTO dto) {
        Set<Veiculo> target = ensureSet(bens.getVeiculos());
        bens.setVeiculos(target);
        target.clear();
        if (dto.getVeiculos() == null) return;
        dto.getVeiculos().forEach(v -> {
            Veiculo entity = veiculoMapper.toEntity(v);
            entity.setBensPosses(bens);
            target.add(entity);
        });
    }

    private void replaceFamiliares(BensPosses bens, BensPossesCompletoDTO dto) {
        Set<FamiliarEscolaParticular> target = ensureSet(bens.getFamiliaresEscola());
        bens.setFamiliaresEscola(target);
        target.clear();
        if (dto.getFamiliaresEscola() == null) return;
        dto.getFamiliaresEscola().forEach(f -> {
            FamiliarEscolaParticular entity = familiarMapper.toEntity(f);
            entity.setBensPosses(bens);
            target.add(entity);
        });
    }

    private void replacePessoasComDeficiencia(BensPosses bens, BensPossesCompletoDTO dto) {
        Set<PessoaComDeficiencia> target = ensureSet(bens.getPessoasComDeficiencia());
        bens.setPessoasComDeficiencia(target);
        target.clear();
        if (dto.getPessoasComDeficiencia() == null) return;
        dto.getPessoasComDeficiencia().forEach(p -> {
            PessoaComDeficiencia entity = deficienciaMapper.toEntity(p);
            entity.setBensPosses(bens);
            target.add(entity);
        });
    }

    private void replaceDespesas(BensPosses bens, BensPossesCompletoDTO dto) {
        Set<DespesaMensal> target = ensureSet(bens.getDespesasMensais());
        bens.setDespesasMensais(target);
        target.clear();
        if (dto.getDespesasMensais() == null) return;
        dto.getDespesasMensais().forEach(d -> {
            DespesaMensal entity = despesaMapper.toEntity(d);
            entity.setBensPosses(bens);
            target.add(entity);
        });
    }

    private <T> Set<T> ensureSet(Set<T> current) {
        return current == null ? new HashSet<>() : current;
    }
}
