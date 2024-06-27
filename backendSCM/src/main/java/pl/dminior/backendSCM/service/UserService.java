package pl.dminior.backendSCM.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AccountRepository accountRepository;


    public BigDecimal findBalanceByUsername(String username) {
        return accountRepository.findBalanceByUsername(username);
    }

    @Transactional
    @Modifying
    public BigDecimal updateBalanceById(UUID id, BigDecimal balanceToUpdate) {
        accountRepository.updateBalanceById(id, balanceToUpdate);
        return accountRepository.getById(id).getBalance();

    }
}
