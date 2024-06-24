package pl.dminior.backendSCM.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.model.Product;
import pl.dminior.backendSCM.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product getProductById(UUID productId){
        return productRepository.findProductById(productId);
    }

    public List<Product> getAllProducts(UUID userId) {
        return productRepository.findAllByUserId(userId);
    }
}
