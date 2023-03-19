package com.fragile.cafe_backend.jwt;

import com.fragile.cafe_backend.dao.UserRepo;
import com.fragile.cafe_backend.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    private  com.fragile.cafe_backend.model.User userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByName is {}", username);
        userDetail = userRepo.findByEmail(username);
        if(!Objects.isNull(userDetail))
           return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        else
           throw new UsernameNotFoundException("user not found");
    }


    public com.fragile.cafe_backend.model.User getUserDetails(){
        return userDetail;
    }
}