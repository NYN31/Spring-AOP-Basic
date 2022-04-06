package com.example.spring.aop.controller;

import com.example.spring.aop.dto.ResultResponse;
import com.example.spring.aop.dto.UserDto;
import com.example.spring.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResultResponse getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/user/test")
    public ResponseEntity<?> testException() {
        throw new RuntimeException("Runtime exception occur in test method");
    }
}
