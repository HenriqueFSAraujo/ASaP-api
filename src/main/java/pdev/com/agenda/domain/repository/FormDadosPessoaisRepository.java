package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import java.util.Optional;

public interface FormDadosPessoaisRepository extends JpaRepository<FormDadosPessoais, Long> {
    Optional<FormDadosPessoais> findByUserId(Long userId);
}
