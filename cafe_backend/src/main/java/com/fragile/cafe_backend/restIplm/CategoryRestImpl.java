package com.fragile.cafe_backend.restIplm;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.model.Category;
import com.fragile.cafe_backend.rest.CategoryRest;
import com.fragile.cafe_backend.services.CategoryService;
import com.fragile.cafe_backend.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CategoryRestImpl implements CategoryRest {

    private final CategoryService categoryService;

//    @Override
//    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
//        try{
//            categoryService.addNewCategory(requestMap);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            categoryService.addNewCategory(requestMap);
            return CafeUtils.getResponseEntity("Category added successfully", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            return categoryService.getAllCategory(filterValue);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            categoryService.updateCategory(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
