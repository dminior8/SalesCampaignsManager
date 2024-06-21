package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dminior.backendSCM.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
