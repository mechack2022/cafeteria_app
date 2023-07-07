package com.fragile.cafe_backend.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/product")
public interface ProductRest {

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Map<String, String> mapRequest);
}
