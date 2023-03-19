package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.User;
import com.fragile.cafe_backend.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
     User findByEmail(String email);

//     List<UserWrapper> getAllUser();

     /*The findAllUsers() method returns a list of UserWrapper objects
     that are constructed using the specified fields from the User entity, filtered by the "user" role.*/
     @Query("SELECT NEW com.fragile.cafe_backend.wrapper.UserWrapper(u.id, u.name, u.contactNumber, u.email, u.status) FROM User u WHERE u.role='user'")
     List<UserWrapper> findAllUsers();
}


