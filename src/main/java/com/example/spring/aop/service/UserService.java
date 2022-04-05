package com.example.spring.aop.service;

import com.example.spring.aop.dto.ResultResponse;
import com.example.spring.aop.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResultResponse getAllUser();
    UserDto getUserById(Long id) throws Exception;
}
