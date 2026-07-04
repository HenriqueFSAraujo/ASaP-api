package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.Escola;
import pdev.com.agenda.domain.enuns.TipoEscolaEnum;

import java.util.List;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    List<Escola> findByTipo(TipoEscolaEnum tipo);
    boolean existsByCnpj(String cnpj);
    boolean existsByCnpjAndIdNot(String cnpj, Long id);
}
