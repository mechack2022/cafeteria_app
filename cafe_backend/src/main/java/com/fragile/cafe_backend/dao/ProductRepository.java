package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.Product;
import com.fragile.cafe_backend.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<ProductWrapper> getAllProduct();
}
