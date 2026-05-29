package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.BensPosses;

import java.util.List;
import java.util.Optional;

public interface BensPossesRepository extends JpaRepository<BensPosses, Long> {
    List<BensPosses> findByUserInfoId(Long userInfoId);

    @EntityGraph(attributePaths = {
            "veiculos", "familiaresEscola", "pessoasComDeficiencia", "despesasMensais"
    })
    Optional<BensPosses> findWithItensByUserInfoId(Long userId);

    /**
     * Variante "tolerante a duplicados" — usada pelo upsert, que precisa lidar com o histórico
     * em que o mesmo usuário podia ter múltiplos {@link BensPosses} (bug pré-Sprint 2).
     * Retorna ordenado por id desc para que o registro mais recente venha primeiro.
     */
    @EntityGraph(attributePaths = {
            "veiculos", "familiaresEscola", "pessoasComDeficiencia", "despesasMensais"
    })
    List<BensPosses> findAllWithItensByUserInfoIdOrderByIdDesc(Long userId);
}