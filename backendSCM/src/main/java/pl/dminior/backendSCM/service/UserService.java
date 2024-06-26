package pl.dminior.backendSCM.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.model.Account;
import pl.dminior.backendSCM.model.UserPassword;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.UserPasswordRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPasswordRepository userPasswordRepository;
    private final AccountRepository accountRepository;

    public Optional<UserPassword> findByUsername(String username) {
        return userPasswordRepository.findByUsername(username);
    }

    public BigDecimal findBalanceByUsername(String username) {
        return accountRepository.findBalanceByUsername(username);
    }

    @Transactional
    @Modifying
    public BigDecimal updateBalanceById(UUID id, BigDecimal balanceToUpdate) {
//        Account account = accountRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + id));
//
//        account.setBalance(balanceToUpdate);
//        accountRepository.saveAndFlush(account);
        accountRepository.updateBalanceById(id, balanceToUpdate);
        return accountRepository.getById(id).getBalance();

    }
}
