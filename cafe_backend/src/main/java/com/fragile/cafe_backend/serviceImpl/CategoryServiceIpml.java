package com.fragile.cafe_backend.serviceImpl;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.dao.CategoryRepo;
import com.fragile.cafe_backend.jwt.JwtAuthenticationFilter;
import com.fragile.cafe_backend.model.Category;
import com.fragile.cafe_backend.services.CategoryService;
import com.fragile.cafe_backend.utils.CafeUtils;
import com.google.common.base.Strings;
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
public class CategoryServiceIpml implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    //ADD CATEGORY
    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        log.info("Inside addNew Category");
        try {
            if (jwtAuthenticationFilter.isAdmin()) {
                log.info("is admin pass");
                if (validateCategory(requestMap, false)) {
                    log.info("is validate pass");
                    categoryRepo.save(getCategory(requestMap, false));
                    log.info("category saved pass");
                    return CafeUtils.getResponseEntity("category added successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(Constant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                log.info("is admin did not pass");
                return CafeUtils.getResponseEntity(Constant.UNAUTHORISE_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.error("Internal server error");
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public boolean validateCategory(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    public Category getCategory(Map<String, String> requestmap, boolean isAdd) {
        Category category = new Category();
        if (isAdd) {
            category.setId(Integer.parseInt(requestmap.get("id")));
        }
        category.setName(requestmap.get("name"));
        return category;
    }

    //GET ALL CATEGORY
    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<>(categoryRepo.getAllCategories(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryRepo.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //        UPDATE CATEGORY
    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            if (jwtAuthenticationFilter.isAdmin()) {
                if (validateCategory(requestMap, true)) {
                    Optional<Category> optional = categoryRepo.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        categoryRepo.save(getCategory(requestMap, true));
                        return CafeUtils.getResponseEntity("Category updated successfully", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Category id does not exist", HttpStatus.BAD_REQUEST);
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
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @Override
//    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
//        try {
//            log.info("Before admin inside update category");
//            if (!jwtAuthenticationFilter.isAdmin()) {
//                log.warn("Unauthorized access to update category");
//                return CafeUtils.getResponseEntity(Constant.UNAUTHORISE_ACCESS, HttpStatus.UNAUTHORIZED);
//            }
//
//            if (!validateCategory(requestMap, true)) {
//                log.warn("Invalid data for updating category");
//                return CafeUtils.getResponseEntity(Constant.INVALID_DATA, HttpStatus.BAD_REQUEST);
//            }
//
//            Optional<Category> optional = categoryRepo.findById(requestMap.get("id"));
//            if (!optional.isPresent()) {
//                log.warn("Category id {} does not exist", requestMap.get("id"));
//                return CafeUtils.getResponseEntity("Category id does not exist", HttpStatus.BAD_REQUEST);
//            }
//
//            Category category = getCategory(requestMap, true);
//            categoryRepo.save(category);
//            log.info("Category updated successfully");
//            return CafeUtils.getResponseEntity("Category updated successfully", HttpStatus.OK);
//
//        } catch (Exception ex) {
//            log.error("Error occurred while updating category", ex);
//            return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
