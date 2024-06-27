package pl.dminior.backendSCM.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dminior.backendSCM.repository.AccountRepository;


import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void testFindBalanceByUsername() {
        // Arrange
        String username = "testuser";
        BigDecimal balance = BigDecimal.valueOf(1000);
        when(accountRepository.findBalanceByUsername(username)).thenReturn(balance);

        // Act
        BigDecimal foundBalance = userService.findBalanceByUsername(username);

        // Assert
        assertNotNull(foundBalance);
        assertEquals(balance, foundBalance);
    }

}
