package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agenda.domain.entity.UserInfo;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

       boolean existsByEmail(String email);
       boolean existsByUserName(String userName);

       /** Verifica duplicidade de e-mail ignorando o próprio usuário (uso em update). */
       boolean existsByEmailAndIdNot(String email, Long id);

       /** Verifica duplicidade de userName ignorando o próprio usuário (uso em update). */
       boolean existsByUserNameAndIdNot(String userName, Long id);
}

