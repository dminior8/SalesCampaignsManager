package pl.dminior.backendSCM.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.dminior.backendSCM.payloads.response.UserResponse;
import pl.dminior.backendSCM.security.services.AccountDetailsImpl;
import pl.dminior.backendSCM.service.UserService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountControllerTests {

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @Mock
    private AccountDetailsImpl userDetails;

    @InjectMocks
    private AccountController accountController;

    private UUID userId;
    private String username;
    private BigDecimal balance;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        username = "testUser";
        balance = new BigDecimal("100.00");
    }

    @Test
    public void testGetCurrentUser_Authenticated() {
        // Przygotowanie mocków dla tego testu
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(userId);
        when(userDetails.getUsername()).thenReturn(username);
        when(userService.findBalanceByUsername(username)).thenReturn(balance);

        // Wywołanie metody kontrolera
        ResponseEntity<?> responseEntity = accountController.getCurrentUser(authentication);

        // Sprawdzenie statusu odpowiedzi
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Sprawdzenie treści odpowiedzi
        Object responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertInstanceOf(UserResponse.class, responseBody);

        UserResponse userResponse = (UserResponse) responseBody;
        assertEquals(userId, userResponse.getId());
        assertEquals(username, userResponse.getUsername());
        assertEquals(balance, userResponse.getBalance());
    }

    @Test
    public void testGetCurrentUser_Unauthenticated() {
        // Mockowanie zależności w ramach testu
        when(authentication.isAuthenticated()).thenReturn(false);

        // Wywołanie metody kontrolera i sprawdzenie odpowiedzi
        ResponseEntity<?> responseEntity = accountController.getCurrentUser(authentication);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Unauthorized", responseEntity.getBody());
    }

    @Test
    public void testUpdateCurrentUserBalance_Authenticated() {
        // Mockowanie zależności w ramach testu
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(userId);
        when(userService.updateBalanceById(any(UUID.class), any(BigDecimal.class))).thenReturn(new BigDecimal("200.00"));

        Map<String, Object> requestBody = Map.of("balance", "200.00");

        // Wywołanie metody kontrolera i sprawdzenie odpowiedzi
        ResponseEntity<?> responseEntity = accountController.updateCurrentUserBalance(requestBody, authentication);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("\"balance\": \"200.00\"", responseEntity.getBody());
    }

    @Test
    public void testUpdateCurrentUserBalance_InvalidBalanceFormat() {
        // Mockowanie zależności w ramach testu
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(userId);

        Map<String, Object> requestBody = Map.of("invalidKey", "value");

        // Wywołanie metody kontrolera i sprawdzenie odpowiedzi
        ResponseEntity<?> responseEntity = accountController.updateCurrentUserBalance(requestBody, authentication);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid balance format", responseEntity.getBody());
    }

    @Test
    public void testUpdateCurrentUserBalance_Unauthenticated() {
        // Mockowanie zależności w ramach testu
        when(authentication.isAuthenticated()).thenReturn(false);

        Map<String, Object> requestBody = Map.of("balance", "200.00");

        // Wywołanie metody kontrolera i sprawdzenie odpowiedzi
        ResponseEntity<?> responseEntity = accountController.updateCurrentUserBalance(requestBody, authentication);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Unauthorized", responseEntity.getBody());
    }
}
