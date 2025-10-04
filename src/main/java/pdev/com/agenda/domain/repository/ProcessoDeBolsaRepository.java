package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;

import java.util.Optional;

public interface ProcessoDeBolsaRepository extends JpaRepository<ProcessoDeBolsa, Long> {
    Optional<ProcessoDeBolsa> findByUser_Id(Long userId);
}
