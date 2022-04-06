package com.example.spring.aop.service;

import com.example.spring.aop.dto.ResultResponse;
import com.example.spring.aop.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResultResponse getAllUser();
    UserDto getUserById(Long id) throws Exception;
}
