package pl.dminior.backendSCM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.payloads.response.UserResponse;
import pl.dminior.backendSCM.security.services.AccountDetailsImpl;
import pl.dminior.backendSCM.service.UserService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AccountController {
    private final UserService userService;


    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
            UUID id = userDetails.getId();
            String username = userDetails.getUsername();
            BigDecimal balance = userService.findBalanceByUsername(username); // Zakładając, że UserDetailsImpl ma metodę getBalance()

            UserResponse userResponse = new UserResponse(id,username, balance);
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PutMapping("/current/balance")
    public ResponseEntity<?> updateCurrentUserBalance(@RequestBody Map<String, Object> requestBody, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
            UUID id = userDetails.getId();

            if (requestBody.containsKey("balance")) {
                Object balanceObject = requestBody.get("balance");
                BigDecimal balanceToUpdate = new BigDecimal(balanceObject.toString());
                userService.updateBalanceById(id, balanceToUpdate);
                return ResponseEntity.ok("\"balance\": " + "\"" + userService.updateBalanceById(id, balanceToUpdate) + "\"");
            } else {
                    return ResponseEntity.badRequest().body("Invalid balance format");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}

