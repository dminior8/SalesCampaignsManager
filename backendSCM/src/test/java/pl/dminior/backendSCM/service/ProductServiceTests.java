package pl.dminior.backendSCM.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.dminior.backendSCM.model.Product;
import pl.dminior.backendSCM.repository.ProductRepository;
import pl.dminior.backendSCM.repository.ProductRepositoryTests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        when(productRepository.findProductById(productId)).thenReturn(expectedProduct);

        // Act
        Product result = productService.getProductById(productId);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productRepository, times(1)).findProductById(productId);
    }

    @Test
    public void testGetAllProductsByUserId() {
        // Arrange
        UUID userId = UUID.randomUUID();
        List<Product> expectedProducts = new ArrayList<>();
        when(productRepository.findAllByAccountId(userId)).thenReturn(expectedProducts);

        // Act
        List<Product> result = productService.getAllProductsByUserId(userId);

        // Assert
        assertEquals(expectedProducts, result);
        verify(productRepository, times(1)).findAllByAccountId(userId);
    }
}
