package com.teja.springboot.service;

import com.teja.springboot.dto.UserDto;
import com.teja.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id,UserDto userDto);
    void deleteUser(Long id);
}
