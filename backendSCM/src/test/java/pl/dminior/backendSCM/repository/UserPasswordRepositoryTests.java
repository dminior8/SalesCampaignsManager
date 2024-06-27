//package pl.dminior.backendSCM.repository;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import pl.dminior.backendSCM.model.UserPassword;
//
//import java.util.Optional;
//
//@DataJpaTest
//public class UserPasswordRepositoryTests {
//
//    @Autowired
//    private UserPasswordRepository userPasswordRepository;
//
//    @Test
//    public void testFindByUsername() {
//        // Arrange
//        String username = "testuser";
//        UserPassword userPassword = new UserPassword();
//        userPassword.setUsername(username);
//        userPassword.setPassword("testpassword");
//        userPasswordRepository.save(userPassword);
//
//        // Act
//        Optional<UserPassword> foundUserPassword = userPasswordRepository.findByUsername(username);
//
//        // Assert
//        Assertions.assertTrue(foundUserPassword.isPresent());
//        Assertions.assertEquals(username, foundUserPassword.get().getUsername());
//    }
//
//    @Test
//    public void testFindByUsername_NotFound() {
//        // Arrange
//        String username = "nonexistentuser";
//
//        // Act
//        Optional<UserPassword> foundUserPassword = userPasswordRepository.findByUsername(username);
//
//        // Assert
//        Assertions.assertFalse(foundUserPassword.isPresent());
//    }
//}
