package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.controller.ProcessoDeBolsa;

public interface ProcessoDeBolsaRepository extends JpaRepository<ProcessoDeBolsa, Long> {
}
