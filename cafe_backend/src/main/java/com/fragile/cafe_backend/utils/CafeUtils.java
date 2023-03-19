package com.fragile.cafe_backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    private CafeUtils(){}

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus status){
     return new  ResponseEntity<>("{\" message \" : \" " + responseMessage + " \"}", status);
    }
}
