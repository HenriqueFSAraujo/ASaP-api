package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormDadosParentesRepository extends JpaRepository<FormDadosParentes, Long> {

    List<FormDadosParentes> findByParent1Cpf(String cpf);

    Optional<FormDadosParentes> findByUser(UserInfo user);

    Optional<FormDadosParentes> findByUser_Id(Long userId);

}
