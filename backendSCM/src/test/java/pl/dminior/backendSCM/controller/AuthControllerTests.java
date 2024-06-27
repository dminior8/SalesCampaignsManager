package pl.dminior.backendSCM.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dminior.backendSCM.payloads.request.LoginRequest;
import pl.dminior.backendSCM.payloads.response.JwtResponse;
import pl.dminior.backendSCM.security.services.LoginServiceImpl;
import pl.dminior.backendSCM.model.EnumRole;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthControllerTests {

    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser_Success() {
        // Przygotowanie danych testowych
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        UUID userId = UUID.randomUUID();
        String token = "mocked_jwt_token";
        EnumRole role = EnumRole.ROLE_USER;

        // Mockowanie zachowania serwisu logowania
        when(loginService.getJwtResponseResponseEntity(any(LoginRequest.class)))
                .thenReturn(ResponseEntity.ok(new JwtResponse(token, userId, loginRequest.getUsername(), role)));

        // Wywołanie kontrolera
        ResponseEntity<JwtResponse> responseEntity = authController.loginUser(loginRequest);

        // Sprawdzenie odpowiedzi
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(token, responseEntity.getBody().getToken());
        assertEquals(userId, responseEntity.getBody().getId());
        assertEquals(loginRequest.getUsername(), responseEntity.getBody().getUsername());
        assertEquals(role, responseEntity.getBody().getRole());
    }

    @Test
    public void testLoginUser_Failure() {
        // Przygotowanie danych testowych
        LoginRequest loginRequest = new LoginRequest("testuser", "invalid_password");

        // Mockowanie zachowania serwisu logowania
        when(loginService.getJwtResponseResponseEntity(any(LoginRequest.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

        // Wywołanie kontrolera
        ResponseEntity<JwtResponse> responseEntity = authController.loginUser(loginRequest);

        // Sprawdzenie odpowiedzi
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody()); // Można również asercję na body, w zależności od rzeczywistego zachowania
    }
}
