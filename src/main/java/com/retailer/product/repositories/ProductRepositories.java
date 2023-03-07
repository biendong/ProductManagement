package com.retailer.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.product.models.Product;

public interface ProductRepositories extends JpaRepository<Product, Integer> {

}
