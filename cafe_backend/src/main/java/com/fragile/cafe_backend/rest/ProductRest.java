package com.fragile.cafe_backend.rest;

import com.fragile.cafe_backend.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/products")
public interface ProductRest {

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Map<String, String> mapRequest);

    @GetMapping("/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PostMapping("/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String, String> mapRequest );

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id);

    @PostMapping("/updateStatus/")
    ResponseEntity<String> updateProductStatus(Map<String, String>  requestMap);

    @GetMapping("/category/{categoryId}/product")
    ResponseEntity<List<ProductWrapper>> getProductByCategory(@PathVariable("categoryId") Integer categoryId);

    @GetMapping("/{productId}")
    ResponseEntity<ProductWrapper> getProductById(@PathVariable("productId") Integer productId);

}
