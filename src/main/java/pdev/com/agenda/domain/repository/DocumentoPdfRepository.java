package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DocumentoPdf;

import java.util.List;

public interface DocumentoPdfRepository extends JpaRepository<DocumentoPdf, Long> {
    List<DocumentoPdf> findAllByUserInfoId(Long userInfoId);
    List<DocumentoPdf> findAllByUserInfoIdAndTipoDocumento(Long userInfoId, String tipoDocumento);
    long countByUserInfoIdAndTipoDocumento(Long userInfoId, String tipoDocumento);
}
