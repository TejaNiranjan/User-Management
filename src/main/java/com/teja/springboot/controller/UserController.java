package com.teja.springboot.controller;

import com.teja.springboot.dto.UserDto;
import com.teja.springboot.entity.User;
import com.teja.springboot.exception.ErrorResponse;
import com.teja.springboot.exception.ResourceNotFoundException;
import com.teja.springboot.service.serviceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceimpl;


    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody  UserDto userDto){
      UserDto savedUser=  userServiceimpl.createUser(userDto);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
       UserDto retrivedUser= userServiceimpl.getUserById(id);
       return ResponseEntity.ok(retrivedUser);
    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
       List<UserDto> allUsers= userServiceimpl.getAllUsers();
       return ResponseEntity.ok(allUsers);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody @Valid UserDto userDto){
        UserDto user1=userServiceimpl.updateUser(id,userDto);
        return new ResponseEntity<>(user1,HttpStatus.OK);

    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userServiceimpl.deleteUser(id);
        return new ResponseEntity<>("user deleted successfully",HttpStatus.OK);
    }



}
