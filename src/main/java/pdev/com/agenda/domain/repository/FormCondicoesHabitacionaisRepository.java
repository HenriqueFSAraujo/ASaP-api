package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.Optional;

@Repository
public interface FormCondicoesHabitacionaisRepository extends JpaRepository<FormCondicoesHabitacionais, Long> {

    Optional<FormCondicoesHabitacionais> findByUser(UserInfo user);
    Optional<FormCondicoesHabitacionais> findByUser_Id(Long userId);
}
