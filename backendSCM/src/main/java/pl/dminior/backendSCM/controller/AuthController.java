package pl.dminior.backendSCM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.repository.CampaignRepository;
import pl.dminior.backendSCM.payloads.request.LoginRequest;
import pl.dminior.backendSCM.payloads.response.JwtResponse;
import pl.dminior.backendSCM.security.services.LoginServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginServiceImpl loginServiceImpl;

    CampaignRepository campaignRepository;
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return loginServiceImpl.getJwtResponseResponseEntity(loginRequest);
    }
    @GetMapping("/test")
    public List<?> getUsersList(){
        List<Campaign> campaigns = campaignRepository.findAll();
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns found in the database.");
        } else {
            System.out.println("Found " + campaigns.size() + " campaigns in the database.");
        }
        return campaigns;

//        List<Account> accounts = accountRepository.findAll();
//        if (accounts.isEmpty()) {
//            System.out.println("No campaigns found in the database.");
//        } else {
//            System.out.println("Found " + accounts.size() + " campaigns in the database.");
//        }
//        return accounts;
    }
}
