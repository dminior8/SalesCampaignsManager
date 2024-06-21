package pl.dminior.backendSCM.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.model.Account;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.repository.CampaignsRepository;
//import pl.dminior.backendSCM.security.jwt.JwtUtils;
//import pl.dminior.backendSCM.security.services.UserDetailsImpl;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    CampaignService campaignService;
//
    @Autowired
CampaignsRepository campaignsRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        return ResponseEntity.ok(new JwtResponse(null, //jwt
//                userDetails.getId(),
//                userDetails.getUsername()));
//    }
    @GetMapping("/test")
    public List<?> getUsersList(){
        List<Campaign> campaigns = campaignsRepository.findAll();
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
