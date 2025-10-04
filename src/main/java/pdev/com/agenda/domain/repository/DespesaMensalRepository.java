package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DespesaMensal;
import java.util.Optional;

public interface DespesaMensalRepository extends JpaRepository<DespesaMensal, Long> {
    Optional<DespesaMensal> findByUserId(Long userId);
}

