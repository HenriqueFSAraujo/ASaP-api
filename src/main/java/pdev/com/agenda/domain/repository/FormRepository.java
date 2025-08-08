package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.controller.FormController;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<FormDadosPessoais, Long> {

    Optional<FormDadosPessoais> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
