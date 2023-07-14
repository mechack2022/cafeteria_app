package com.fragile.cafe_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@NamedQuery(name = "Product.getAllProduct", query = "SELECT new com.fragile.cafe_backend.wrapper.ProductWrapper(p.id, p.name, p.status, p.description, p.price, p.category.id, p.category.name) FROM Product p")


//@NamedQuery(name="Product.getAllProduct", query="select new com.fragile.cafe_backend.wrapper.ProductWrapper(p.id, p.name, p.Status, p.price, p.category.id,p.category.name) from  Product p";
////com.fragile.cafe_backend.wrapper.UserWrapper(u.id, u.name, u.contactNumber, u.email, u.status) FROM User

@Table(name = "products")
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Entity
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_FK", nullable = false)
    private Category category;

    private String description;

    private Integer price;

    private String status;
}
