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
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        //return ResponseEntity.ok(userService.getUserById(id));
        try{
            UserDto userDto = userService.getUserById(id);
            return ResponseEntity
                    .ok()
                    .header("Messagge", "User Found")
                    .body(userDto);
        } catch(NullPointerException e) {
            return ResponseEntity.internalServerError()
                    .header("Message", "Internal server error!!")
                    .build();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("message", e.getMessage())
                    .build();
        }
    }
}
