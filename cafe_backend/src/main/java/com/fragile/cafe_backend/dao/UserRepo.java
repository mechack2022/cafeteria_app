package com.fragile.cafe_backend.dao;

import com.fragile.cafe_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
     User findByEmail(String email);
}


