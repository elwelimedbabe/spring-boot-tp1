package com.tp1.exo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tp1.exo.entity.Product;
import com.tp1.exo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // جلب جميع المنتجات
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // جلب منتج حسب ID
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // حفظ منتج جديد أو تعديل منتج موجود
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // حذف منتج حسب ID
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
