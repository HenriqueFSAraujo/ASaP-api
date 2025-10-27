package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;

import java.util.List;
import java.util.Optional;

public  interface DocumentosGeraisPdfRepository extends JpaRepository<DocumentosGeraisPdf, Long> {
    Optional<DocumentosGeraisPdf> findByUserInfoId(Long userInfoId);
    List<DocumentosGeraisPdf> findAllByUserInfoId(Long userInfoId);
}