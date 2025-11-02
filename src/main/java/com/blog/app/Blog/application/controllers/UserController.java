package com.blog.app.Blog.application.controllers;

import com.blog.app.Blog.application.entites.User;
import com.blog.app.Blog.application.paylods.UserDTO;
import com.blog.app.Blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUserDto = this.userService.createUser(userDTO);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
}
