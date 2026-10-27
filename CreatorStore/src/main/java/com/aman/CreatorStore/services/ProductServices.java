package com.aman.CreatorStore.services;

import com.aman.CreatorStore.entities.Product;
import com.aman.CreatorStore.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }


    public Product updateProduct(Product product,Long id){
        Product existingProduct=productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id "+id));

        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        return productRepository.save(existingProduct) ;
    }


    public Product getProductById(Long id ){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found by id "+id));
    }


    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);

    }


}
