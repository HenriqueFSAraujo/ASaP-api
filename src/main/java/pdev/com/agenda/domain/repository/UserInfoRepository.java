package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.UserInfo;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

       boolean existsByEmail(String email);
       boolean existsByUserName(String userName);
}

