package pdev.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdev.com.agenda.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
