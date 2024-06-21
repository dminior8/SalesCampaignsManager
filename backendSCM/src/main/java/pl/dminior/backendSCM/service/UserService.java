package pl.dminior.backendSCM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.model.UserPassword;
import pl.dminior.backendSCM.repository.UserPasswordRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserPasswordRepository userPasswordRepository;

    public UserService(UserPasswordRepository userPasswordRepository) {
        this.userPasswordRepository = userPasswordRepository;
    }


    public Optional<UserPassword> findByUsername(String username) {
        return userPasswordRepository.findByUsername(username);
    }
}
