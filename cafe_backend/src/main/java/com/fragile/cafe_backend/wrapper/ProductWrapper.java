package com.fragile.cafe_backend.wrapper;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class ProductWrapper {
    private Integer id;

    private String name;

    private Integer price;

    private String status;

    private String description;

    private Integer categoryId;

    private String categoryName;


    public ProductWrapper(Integer id, String name, String status, String description, Integer price, Integer categoryId, String categoryName){
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;

    }


}
