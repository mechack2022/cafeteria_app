package com.fragile.cafe_backend.services;

import com.fragile.cafe_backend.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requesMap);

    ResponseEntity<List<UserWrapper>> getAllUsers();

    ResponseEntity<String> updateUserStatus(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();
    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgetPassword(Map<String , String> requestMap);
}
