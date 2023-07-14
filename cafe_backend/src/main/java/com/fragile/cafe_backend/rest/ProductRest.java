package com.fragile.cafe_backend.rest;

import com.fragile.cafe_backend.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
public interface ProductRest {

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Map<String, String> mapRequest);

    @GetMapping("/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PostMapping("/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> mapRequest );
}
