package com.fragile.cafe_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;



//@NamedQuery(name="User.getAllUser", query="SELECT NEW com.fragile.cafe_backend.wrapper.UserWrapper(u.id, u.name, u.contactNumber, u.email, u.status) FROM User u WHERE u.role='user'")
//@NamedQuery(name="User.updateUserStatus", query="update User u set u.status=:status where u.id=:id ")
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
