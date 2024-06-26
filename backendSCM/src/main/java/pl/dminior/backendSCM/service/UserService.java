package pl.dminior.backendSCM.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
