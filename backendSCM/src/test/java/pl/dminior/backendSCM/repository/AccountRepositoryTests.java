package pl.dminior.backendSCM.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dminior.backendSCM.model.Account;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTests {

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void testFindByUsername() {
        // Arrange
        String username = "testuser";
        Account account = new Account();
        account.setUsername(username);
        when(accountRepository.findByUsername(username)).thenReturn(account);

        // Act
        Account foundAccount = accountRepository.findByUsername(username);

        // Assert
        assertNotNull(foundAccount);
        assertEquals(username, foundAccount.getUsername());
    }

    @Test
    public void testFindBalanceByUsername() {
        // Arrange
        String username = "testuser";
        BigDecimal balance = BigDecimal.valueOf(1000);
        when(accountRepository.findBalanceByUsername(username)).thenReturn(balance);

        // Act
        BigDecimal foundBalance = accountRepository.findBalanceByUsername(username);

        // Assert
        assertNotNull(foundBalance);
        assertEquals(balance, foundBalance);
    }

    @Test
    public void testUpdateBalanceById() {
        // Arrange
        UUID accountId = UUID.randomUUID();
        BigDecimal newBalance = BigDecimal.valueOf(2000);
        doNothing().when(accountRepository).updateBalanceById(accountId, newBalance);

        // Act
        accountRepository.updateBalanceById(accountId, newBalance);

        // Assert
        verify(accountRepository, times(1)).updateBalanceById(accountId, newBalance);
    }

    @Test
    public void testGetBalanceById() {
        // Arrange
        UUID accountId = UUID.randomUUID();
        BigDecimal balance = BigDecimal.valueOf(1000);
        when(accountRepository.getBalanceById(accountId)).thenReturn(balance);

        // Act
        BigDecimal foundBalance = accountRepository.getBalanceById(accountId);

        // Assert
        assertNotNull(foundBalance);
        assertEquals(balance, foundBalance);
    }
}
