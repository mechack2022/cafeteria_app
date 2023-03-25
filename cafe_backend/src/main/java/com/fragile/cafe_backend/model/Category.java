package com.fragile.cafe_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;


//@NamedQuery(name = "Category.getAllCategories", query = "SELECT c FROM Category c")
@Table(name = "categories")
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Entity
public class Category {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
