package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    //    @Query("select c from Category c")
    /*
    The query selects all categories (SELECT c FROM Category c) whose id is
     present in the set of categories of products
    (IN (SELECT p.category.id FROM Product p)) with Status equal to "true"
    (WHERE p.status = 'true'). The result is returned as a List<Category>
    */
//    @Query("SELECT c FROM Category c WHERE c.id IN (SELECT p.category FROM Product p WHERE p.Status = 'true')")
    List<Category> getAllCategories();

}
