package pl.dminior.backendSCM.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.model.UserPassword;
import pl.dminior.backendSCM.repository.UserPasswordRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPasswordRepository userPasswordRepository;

    public Optional<UserPassword> findByUsername(String username) {
        return userPasswordRepository.findByUsername(username);
    }
}
