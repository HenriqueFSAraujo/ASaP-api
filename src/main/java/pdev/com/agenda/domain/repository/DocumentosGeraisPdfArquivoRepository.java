package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdfArquivo;

import java.util.List;

public interface DocumentosGeraisPdfArquivoRepository extends JpaRepository<DocumentosGeraisPdfArquivo, Long> {

    List<DocumentosGeraisPdfArquivo> findByUserInfoIdAndTipoDocumentoOrderByDataUploadDesc(Long userId, String tipoDocumento);

    boolean existsByUserInfoId(Long userId);
}
