package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.DespesaMensal;
import java.util.List;

public interface DespesaMensalRepository extends JpaRepository<DespesaMensal, Long> {
    List<DespesaMensal> findByBensPosses_UserInfoId(Long userInfoId);
}
