package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
