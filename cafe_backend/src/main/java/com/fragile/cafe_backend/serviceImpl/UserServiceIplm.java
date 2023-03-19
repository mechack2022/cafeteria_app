package com.fragile.cafe_backend.serviceImpl;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.dao.UserRepo;
import com.fragile.cafe_backend.jwt.CustomUserDetailService;
import com.fragile.cafe_backend.jwt.JwtAuthenticationFilter;
import com.fragile.cafe_backend.jwt.JwtUtils;
import com.fragile.cafe_backend.model.User;
import com.fragile.cafe_backend.services.UserService;
import com.fragile.cafe_backend.utils.CafeUtils;
import com.fragile.cafe_backend.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceIplm implements UserService {

    private final UserRepo userRepo;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailService customUserDetailService;

    private final JwtUtils jwtUtils;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);

        try {
            if (validateSignUpMap(requestMap)) {
//         check if user already exist
                User user = userRepo.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepo.save(mapRequestMapToUser(requestMap));
                    return CafeUtils.getResponseEntity("Successfully register", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email has been taken", HttpStatus.BAD_REQUEST);
                }
            }
            return CafeUtils.getResponseEntity(Constant.INVALID_DATA, HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //    if keys spelling are right, return true
    boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("contactNumber");
    }

    public User mapRequestMapToUser(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requesMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requesMap.get("email"), requesMap.get("password")));
            if (auth.isAuthenticated()) {
                if (customUserDetailService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<>("{\" token \" : \"" +
                            jwtUtils.generateToken(customUserDetailService.getUserDetails().getEmail(), customUserDetailService.getUserDetails().getRole()) + "\"}", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Wait for Admin Approval", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            log.error("An error occurred while login", ex);
        }
        return CafeUtils.getResponseEntity("Bad credentials", HttpStatus.BAD_REQUEST);
    }

    //get all users
    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            if(jwtAuthenticationFilter.isAdmin()){
               return new ResponseEntity<>(userRepo.findAllUsers(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

//    admin update user status to true
    @Override
    public ResponseEntity<String> updateUserStatus(Map<String, String> requestMap) {
        log.info("Inside updateUserStatus");
        try{
            if(jwtAuthenticationFilter.isAdmin()){
                Optional<User> user = userRepo.findById(Integer.parseInt(requestMap.get("id")));
                if(user.isPresent()){
                    userRepo.updateUserStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("User status successfully updated", HttpStatus.OK);
                }else {
                    return CafeUtils.getResponseEntity("User id does not exist", HttpStatus.OK);
                }
            }else{
                return CafeUtils.getResponseEntity(Constant.UNAUTHORISE_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
