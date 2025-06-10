package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;

@Repository
public interface FormCondicoesHabitacionaisRepository extends JpaRepository<FormCondicoesHabitacionais, Long> {
}
