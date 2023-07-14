package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.Product;
import com.fragile.cafe_backend.wrapper.ProductWrapper;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.fragile.cafe_backend.wrapper.ProductWrapper(p.id, p.name, p.status, p.description, p.price, p.category.id, p.category.name) FROM Product p")
    List<ProductWrapper> getAllProduct();

    @Transactional
    @Modifying
    @Query("update Product p set p.status=:status where p.id=:id")
    void updateProductStatus(@Param("status") String status, @Param("id") Integer id);

    @Query("select new com.fragile.cafe_backend.wrapper.ProductWrapper(p.id, p.name) from Product p where p.category.id =:id and p.status='true' ")
    List<ProductWrapper> getProductByCategory(@Param("id") Integer id);

    @Query("select new com.fragile.cafe_backend.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.category.name) from Product p where p.id =:productId and p.status ='true' ")
    ProductWrapper getProductById(@Param("productId") Integer productId);


}
