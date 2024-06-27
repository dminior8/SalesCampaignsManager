package pl.dminior.backendSCM.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.dminior.backendSCM.model.*;
import pl.dminior.backendSCM.security.services.AccountDetailsImpl;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class ProductController {
    private final ProductService productService;


    //Lista produtków dla danego użytkownika
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
            UUID id = userDetails.getId();
            List<Product> products = productService.getAllProductsByUserId(id);

            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

    }

    //Poibieranie nazyw pojedynczego produktu na podstawie ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable UUID productId, Authentication authentication) { //public ResponseEntity<List<CampaignDTO>> getAllCampaignsByProductId(@PathVariable UUID productId) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(productService.getProductById(productId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

    }
}

