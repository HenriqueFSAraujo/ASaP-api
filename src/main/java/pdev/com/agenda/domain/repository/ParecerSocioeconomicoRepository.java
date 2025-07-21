package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.ParecerSocioeconomico;

import java.util.List;

@Repository
public interface ParecerSocioeconomicoRepository extends JpaRepository<ParecerSocioeconomico, Long> {

    List<ParecerSocioeconomico> findAllByUserId(Long userId);
}
