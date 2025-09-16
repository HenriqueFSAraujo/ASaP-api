package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;

public  interface DocumentosGeraisPdfRepository extends JpaRepository<DocumentosGeraisPdf, Long> {
}