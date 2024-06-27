package pl.dminior.backendSCM.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dminior.backendSCM.model.EnumRole;
import pl.dminior.backendSCM.model.Role;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@DataJpaTest
public class RoleRepositoryTests {

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        // Arrange
        Role roleUser = new Role(EnumRole.ROLE_USER);
        roleRepository.save(roleUser);

        // Act
        when(roleRepository.findByName(EnumRole.ROLE_USER)).thenReturn(roleUser);
        Role foundRole = roleRepository.findByName(EnumRole.ROLE_USER);

        // Assert
        Assertions.assertNotNull(foundRole);
        Assertions.assertEquals(roleUser.getName(), foundRole.getName());
    }

    @Test
    public void testFindByName_InvalidName() {
        // Act
        Role foundRole = roleRepository.findByName(EnumRole.ROLE_ADMIN);

        // Assert
        Assertions.assertNull(foundRole);
    }
}
