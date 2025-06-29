package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.BensPosses;

import java.util.List;

public interface BensPossesRepository extends JpaRepository<BensPosses, Long> {


    List<BensPosses> findByUserInfoId(Long userInfoId);
}