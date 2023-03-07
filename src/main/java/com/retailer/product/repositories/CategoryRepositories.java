package com.retailer.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.product.models.Category;

public interface CategoryRepositories extends JpaRepository<Category, Integer> {

}
