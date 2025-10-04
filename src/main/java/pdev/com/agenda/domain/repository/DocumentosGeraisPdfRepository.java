package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;

import java.util.Optional;

public  interface DocumentosGeraisPdfRepository extends JpaRepository<DocumentosGeraisPdf, Long> {
    Optional<DocumentosGeraisPdf> findByUserId(Long userId);
    Optional<DocumentosGeraisPdf> findByUserInfoId(Long userInfoId);
}