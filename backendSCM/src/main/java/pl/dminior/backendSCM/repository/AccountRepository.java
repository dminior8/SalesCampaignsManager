package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.Account;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);

    @Query("SELECT a.balance FROM Account a WHERE a.username = ?1")
    BigDecimal findBalanceByUsername(String username);


    @Modifying
    @Query(value = "UPDATE account SET balance = ?2 WHERE id = ?1", nativeQuery = true)
    void updateBalanceById(UUID accountId, BigDecimal newBalance);



    @Query(value = "SELECT balance FROM account " +
            "WHERE account.id = ?1",
            nativeQuery = true)
    BigDecimal getBalanceById(UUID id);
}
