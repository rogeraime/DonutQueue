package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
}
