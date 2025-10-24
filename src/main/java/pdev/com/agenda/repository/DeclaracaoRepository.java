package pdev.com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.Declaracao;

@Repository
public interface DeclaracaoRepository extends JpaRepository<Declaracao, Long> {
}
