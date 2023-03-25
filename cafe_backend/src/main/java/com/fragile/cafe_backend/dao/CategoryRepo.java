package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, String> {
    @Query("select c from Category c")
    List<Category> getAllCategories();
//    List<Category> getAllCategory();
}
