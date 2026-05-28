package com.example.springrestapi.service;

import com.example.springrestapi.exception.ResourceNotFoundException;
import com.example.springrestapi.model.Product;
import com.example.springrestapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        product.setId(null); // ensure new ID is generated
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        updated.setId(id);
        return repository.save(updated);
    }

    public void deleteProduct(Long id) {
        if (!repository.deleteById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
    }
}