package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dminior.backendSCM.model.UserPassword;

import java.util.Optional;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    Optional<UserPassword> findByUsername(String username);
}
