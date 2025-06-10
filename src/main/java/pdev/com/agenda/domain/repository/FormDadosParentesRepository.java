package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormDadosParentes;

import java.util.List;

@Repository
public interface FormDadosParentesRepository extends JpaRepository<FormDadosParentes, Long> {

    List<FormDadosParentes> findByParent1Cpf(String cpf);
}
