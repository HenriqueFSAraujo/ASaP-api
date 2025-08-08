package pdev.com.agenda.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.Optional;

@Repository
public interface FormEnderecoCandidatoRepository  extends JpaRepository<FormEnderecoCandidato, Long> {
    Optional<FormEnderecoCandidato> findByCep(String cep);
    Optional<FormEnderecoCandidato> findByUser(UserInfo user);
    Optional<FormEnderecoCandidato> findByUser_Id(Long userId);
}
