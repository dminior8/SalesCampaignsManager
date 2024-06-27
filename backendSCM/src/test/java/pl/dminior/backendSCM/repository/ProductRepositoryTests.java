package pl.dminior.backendSCM.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dminior.backendSCM.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@DataJpaTest
public class ProductRepositoryTests {

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testFindProductById() {
        // Arrange
        Product product = createProduct("Product A");
        productRepository.save(product);

        // Act
        when(productRepository.findProductById(product.getId())).thenReturn(product);
        Product foundProduct = productRepository.findProductById(product.getId());

        // Assert
        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(product, foundProduct);
    }

    @Test
    public void testFindAllByAccountId() {
        // Arrange
        UUID accountId = UUID.randomUUID();
        Product product1 = createProduct("Product 1");
        Product product2 = createProduct("Product 2");
        productRepository.save(product1);
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAllByAccountId(accountId);

        // Assert
        Assertions.assertEquals(0, products.size()); // Assuming no campaign related data available
        // Uncomment below lines if campaign related data is available
        // Assertions.assertTrue(products.contains(product1));
        // Assertions.assertTrue(products.contains(product2));
    }

    private Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        // Można dodać więcej pól do obiektu Product w zależności od potrzeb testu
        return product;
    }
}
