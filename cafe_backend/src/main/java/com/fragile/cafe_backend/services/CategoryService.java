package com.fragile.cafe_backend.services;

import com.fragile.cafe_backend.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

    ResponseEntity<List<Category>> getAllCategory(String filterValue);


    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
