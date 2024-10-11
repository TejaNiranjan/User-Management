package com.teja.springboot.service.serviceImpl;

import com.teja.springboot.dto.UserDto;
import com.teja.springboot.entity.User;
import com.teja.springboot.exception.EmailAlreadyExistsException;
import com.teja.springboot.exception.ResourceNotFoundException;
import com.teja.springboot.mapper.UserMapper;
import com.teja.springboot.repository.UserRepository;
import com.teja.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already exists for user");
        }
        //convert into userdto to user
        User user= modelMapper.map(userDto,User.class);

        User user1=userRepository.save(user);
        //convert user to userdto
        UserDto userDto1=modelMapper.map(user1,UserDto.class);
       return  userDto1;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> l=userRepository.findAll();
        return l.stream().map((user)->modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
       User retrivedUser=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));
       retrivedUser.setFirstName(user.getFirstName());
       retrivedUser.setLastName(user.getLastName());
       retrivedUser.setEmail(user.getEmail());
        User user1= userRepository.save(retrivedUser);
        return modelMapper.map(user1,UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));
        userRepository.deleteById(id);
    }
}
