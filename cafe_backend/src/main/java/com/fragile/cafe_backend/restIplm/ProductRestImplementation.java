package com.fragile.cafe_backend.restIplm;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.model.Category;
import com.fragile.cafe_backend.rest.ProductRest;
import com.fragile.cafe_backend.services.ProductService;
import com.fragile.cafe_backend.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductRestImplementation implements ProductRest {

    private final ProductService productService;
    @Override
    public ResponseEntity<String> addProduct(Map<String, String> mapRequest) {
        try{
           return productService.addProduct(mapRequest);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
