package pl.dminior.backendSCM.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dminior.backendSCM.model.Account;
import pl.dminior.backendSCM.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        return AccountDetailsImpl.build(account);
    }

}