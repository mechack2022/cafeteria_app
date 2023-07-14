package com.fragile.cafe_backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@RequiredArgsConstructor
public class ProductNotFoundException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public ProductNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
