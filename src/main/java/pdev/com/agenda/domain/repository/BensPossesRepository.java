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
}