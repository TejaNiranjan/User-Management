package com.teja.springboot.mapper;

import com.teja.springboot.dto.UserDto;
import com.teja.springboot.entity.User;

public class UserMapper {
    public static UserDto mapToUserdto(User user){
        UserDto userDto =new UserDto(
                user.getId(),user.getFirstName(),user.getLastName(),user.getEmail()
        );
        return userDto;
    }
    public  static  User mapToUser(UserDto userDto){
        User user=new User(
                userDto.getId(),userDto.getFirstName(),userDto.getLastName(),userDto.getEmail()
        );
        return user;
    }
}
