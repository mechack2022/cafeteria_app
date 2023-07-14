package com.fragile.cafe_backend.rest;

import com.fragile.cafe_backend.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RequestMapping("/category")
public interface CategoryRest {

    @PostMapping("/add")
    ResponseEntity<String> addNewCategory(@RequestBody Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<Category>>  getAllCategory(@RequestParam(required = false) String filterValue);

    @PostMapping("/update")
    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
