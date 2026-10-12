package com.aman.CreatorStore.controller;

import com.aman.CreatorStore.entities.Product;
import com.aman.CreatorStore.services.ProductServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices productService;

    @PostMapping()
    public Product createProduct(@Valid @RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@Valid @RequestBody Product product,@PathVariable Long id){
        return productService.updateProduct(product,id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id ){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product>getProducts(){
        return productService.getProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id){
        productService.deleteProduct(id);

    }
}
