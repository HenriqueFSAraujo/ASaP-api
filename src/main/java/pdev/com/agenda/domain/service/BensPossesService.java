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

@Service
@AllArgsConstructor
public class BensPossesService {

    private final BensPossesRepository bensPossesRepository;
    private final UserInfoRepository userInfoRepository;

    private final VeiculoMapper veiculoMapper;
    private final FamiliarEscolaParticularMapper familiarMapper;
    private final PessoaComDeficienciaMapper deficienciaMapper;
    private final DespesaMensalMapper despesaMapper;


    public BensPosses salvarCompleto(BensPossesCompletoDTO dto) {
        UserInfo user = userInfoRepository.findById(dto.getUserInfoId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        BensPosses bens = new BensPosses();
        bens.setUserInfo(user);

        List<Veiculo> veiculos = dto.getVeiculos().stream()
                .map(veiculoMapper::toEntity)
                .peek(v -> v.setBensPosses(bens))
                .toList();

        List<FamiliarEscolaParticular> familiares = dto.getFamiliaresEscola().stream()
                .map(familiarMapper::toEntity)
                .peek(f -> f.setBensPosses(bens))
                .toList();

        List<PessoaComDeficiencia> deficiencias = dto.getPessoasComDeficiencia().stream()
                .map(deficienciaMapper::toEntity)
                .peek(p -> p.setBensPosses(bens))
                .toList();

        List<DespesaMensal> despesas = dto.getDespesasMensais().stream()
                .map(despesaMapper::toEntity)
                .peek(d -> d.setBensPosses(bens))
                .toList();

        bens.setVeiculos(new HashSet<>(veiculos));
        bens.setFamiliaresEscola(new HashSet<>(familiares));
        bens.setPessoasComDeficiencia(new HashSet<>(deficiencias));
        bens.setDespesasMensais(new HashSet<>(despesas));
        bens.setStatus("PENDENTE");
        return bensPossesRepository.save(bens);
    }
    @Transactional(readOnly = true)
    public BensPosses buscarPorUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId não pode ser nulo");
        }
        return bensPossesRepository.findWithItensByUserInfoId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Bens/Posse não encontrado para o usuário: " + userId));
    }

}