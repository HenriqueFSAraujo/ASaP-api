package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserInfo, Long> {
    public Optional<UserInfo> findByUserName(String user);

}
