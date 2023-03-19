package com.fragile.cafe_backend.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requesMap);
}
