package com.fragile.cafe_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@Table(name="users")
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Entity
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Serial
    private static final long serialVersionUID = 1L;


   private String name;

   @Column(name="contactNumber")
   private String contactNumber;

   private String email;

   private String password;

   private String status;

   private String role;
}
