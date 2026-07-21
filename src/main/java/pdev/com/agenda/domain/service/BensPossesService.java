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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public BensPosses salvarCompleto(BensPossesCompletoDTO dto) {
        UserInfo user = userInfoRepository.findById(dto.getUserInfoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuario nao encontrado com ID: " + dto.getUserInfoId()));

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
    public BensPossesCompletoDTO buscarPorUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId nao pode ser nulo");
        }
        return bensPossesRepository.findAllWithItensByUserInfoIdOrderByIdDesc(userId).stream()
                .findFirst()
                .map(this::toCompletoDTO)
                .orElse(null);
    }

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
            entity.setId(null);
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
            entity.setId(null);
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
            entity.setId(null);
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
            entity.setId(null);
            entity.setBensPosses(bens);
            target.add(entity);
        });
    }

    private BensPossesCompletoDTO toCompletoDTO(BensPosses bens) {
        return BensPossesCompletoDTO.builder()
                .userInfoId(bens.getUserInfo() != null ? bens.getUserInfo().getId() : null)
                .veiculos(bens.getVeiculos() == null ? List.of() : bens.getVeiculos().stream()
                        .sorted(Comparator.comparing(Veiculo::getId, Comparator.nullsLast(Long::compareTo)))
                        .map(veiculoMapper::toDTO)
                        .collect(Collectors.toList()))
                .familiaresEscola(bens.getFamiliaresEscola() == null ? List.of() : bens.getFamiliaresEscola().stream()
                        .sorted(Comparator.comparing(FamiliarEscolaParticular::getId, Comparator.nullsLast(Long::compareTo)))
                        .map(familiarMapper::toDTO)
                        .collect(Collectors.toList()))
                .pessoasComDeficiencia(bens.getPessoasComDeficiencia() == null ? List.of() : bens.getPessoasComDeficiencia().stream()
                        .sorted(Comparator.comparing(PessoaComDeficiencia::getId, Comparator.nullsLast(Long::compareTo)))
                        .map(deficienciaMapper::toDTO)
                        .collect(Collectors.toList()))
                .despesasMensais(bens.getDespesasMensais() == null ? List.of() : bens.getDespesasMensais().stream()
                        .sorted(Comparator.comparing(DespesaMensal::getId, Comparator.nullsLast(Long::compareTo)))
                        .map(despesaMapper::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private <T> Set<T> ensureSet(Set<T> current) {
        return current == null ? new HashSet<>() : current;
    }
}
