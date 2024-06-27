package pl.dminior.backendSCM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.payloads.request.LoginRequest;
import pl.dminior.backendSCM.payloads.response.JwtResponse;
import pl.dminior.backendSCM.security.services.LoginServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginServiceImpl loginServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return loginServiceImpl.getJwtResponseResponseEntity(loginRequest);
    }
}
