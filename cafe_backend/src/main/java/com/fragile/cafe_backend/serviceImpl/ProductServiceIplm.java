package com.fragile.cafe_backend.serviceImpl;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.dao.ProductRepository;
import com.fragile.cafe_backend.jwt.JwtAuthenticationFilter;
import com.fragile.cafe_backend.model.Category;
import com.fragile.cafe_backend.model.Product;
import com.fragile.cafe_backend.services.ProductService;
import com.fragile.cafe_backend.utils.CafeUtils;
import com.fragile.cafe_backend.wrapper.ProductWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceIplm implements ProductService {

    private final ProductRepository productRepo;

    private final JwtAuthenticationFilter jwtFilter;

    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProduct(requestMap, false)) {
                    productRepo.save(getProductFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Product added successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(Constant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else return CafeUtils.getResponseEntity(Constant.UNAUTHORISE_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return new ResponseEntity<>(productRepo.getAllProduct(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProduct(requestMap, true)) {
                    Optional<Product> foundProduct = productRepo.findById(Integer.parseInt(requestMap.get("id")));
                    if (foundProduct.isPresent()) {
                        Product newProduct = getProductFromMap(requestMap, true);
                        newProduct.setStatus(foundProduct.get().getStatus());
                        productRepo.save(newProduct);
                        return CafeUtils.getResponseEntity("Product updated successfully", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Product id not found", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return CafeUtils.getResponseEntity(Constant.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(Constant.UNAUTHORISE_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProduct(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setName(requestMap.get("name"));
        product.setCategory(category);
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }

}
