package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.ERole;
import pl.dminior.backendSCM.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);
}
