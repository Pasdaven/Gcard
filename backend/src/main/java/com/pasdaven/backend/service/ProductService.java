package com.pasdaven.backend.service;

import com.pasdaven.backend.model.ProductEntity;
import com.pasdaven.backend.repo.ProductRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductEntity getProduct(Integer id) {
        return productRepo.findById(id).get();
    }

    public ProductEntity saveProduct(ProductEntity product) {
        return productRepo.save(product);
    }

}
