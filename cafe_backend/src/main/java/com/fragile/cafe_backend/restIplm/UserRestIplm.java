package com.fragile.cafe_backend.restIplm;

import com.fragile.cafe_backend.constant.Constant;
import com.fragile.cafe_backend.rest.UserRest;
import com.fragile.cafe_backend.services.UserService;
import com.fragile.cafe_backend.utils.CafeUtils;
import com.fragile.cafe_backend.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestIplm  implements UserRest {

    private final UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> request) {
      try{
       return userService.signUp(request);
      }catch(Exception ex){
          ex.printStackTrace();
      }
      return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requesMap){
        try{
            return userService.login(requesMap);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            return userService.getAllUsers();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateUserStatus(Map<String, String> requestMap) {
        try{
            return userService.updateUserStatus(requestMap);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Constant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
