package com.example.spring.aop.service;

import com.example.spring.aop.dto.ResultResponse;
import com.example.spring.aop.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private List<UserDto> userList = null;

    public UserServiceImpl() {
        userList = new ArrayList<>();
        userList.add(new UserDto(1L, "Karim", "karim12@gmail.com"));
        userList.add(new UserDto(2L,"Rahim", "rahim12@gmail.com"));
    }

    @Override
    public ResultResponse getAllUser(){
        return new ResultResponse(userList);
    }

    @Override
    public UserDto getUserById(Long id) throws RuntimeException {
        for(UserDto userDto : userList) {
            if(userDto.getId() == id) {
                return userDto;
            }
        }
        throw new RuntimeException("User not found by " + id);
    }
}
