package pdev.com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.List;

@Repository
public interface ComposicaoFamiliarRepository extends JpaRepository<ComposicaoFamiliar, Long> {
    List<ComposicaoFamiliar> findAllByUserInfo(UserInfo userInfo);

}
