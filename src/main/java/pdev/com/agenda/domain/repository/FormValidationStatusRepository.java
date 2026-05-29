package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.FormValidationStatus;
import pdev.com.agenda.domain.enuns.FormSectionEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormValidationStatusRepository extends JpaRepository<FormValidationStatus, Long> {

    List<FormValidationStatus> findAllByUserInfo_Id(Long userInfoId);

    Optional<FormValidationStatus> findByUserInfo_IdAndSection(Long userInfoId, FormSectionEnum section);
}
