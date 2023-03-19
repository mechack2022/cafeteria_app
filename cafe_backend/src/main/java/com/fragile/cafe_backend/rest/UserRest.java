package com.fragile.cafe_backend.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/user")
public interface UserRest {

    @PostMapping("/signup")
     ResponseEntity<String> signUp(@RequestBody Map<String, String> request);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Map<String, String> requesMap);
}
