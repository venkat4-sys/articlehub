package com.ait.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.constant.AppConstants;
import com.ait.service.UserService;

@RestController
@RequestMapping(AppConstants.USER_BASE_URL)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(AppConstants.USER_SIGNUP_URL)
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> user) {
        boolean result = userService.signup(user);
        if (result) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(AppConstants.USER_LOGIN_URL)
    public ResponseEntity<?> login(@RequestBody Map<String, Object> user) {
        boolean result = userService.login(user);
        if (result) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(AppConstants.USER_ALL_URL)
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {
        List<Map<String, Object>> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
