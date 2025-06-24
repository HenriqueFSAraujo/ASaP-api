package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.UserInfoResponse;
import pdev.com.agenda.domain.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

       Optional<UserInfo> findByEmail(String email);

       public Optional<UserInfoResponse> findByUserName(String userName);
}
