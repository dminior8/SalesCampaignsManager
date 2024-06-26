package pl.dminior.backendSCM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.security.payloads.response.UserResponse;
import pl.dminior.backendSCM.security.services.UserDetailsImpl;
import pl.dminior.backendSCM.service.UserService;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            UUID id = userDetails.getId();
            String username = userDetails.getUsername();
            BigDecimal balance = userService.findBalanceByUsername(username); // Zakładając, że UserDetailsImpl ma metodę getBalance()

            UserResponse userResponse = new UserResponse(id,username, balance);
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}

