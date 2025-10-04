package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import java.util.Optional;

public interface FormDadosPessoaisRepository extends JpaRepository<FormDadosPessoais, Long> {
    // Verifique se o campo userId existe na entidade FormDadosPessoais. Se não existir, ajuste para o relacionamento correto
    Optional<FormDadosPessoais> findByUserId(Long userId);
}
