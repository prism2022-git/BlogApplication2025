package com.blog.app.Blog.application.controllers;

//import com.blog.app.Blog.application.paylods;
import com.blog.app.Blog.application.paylods.UserDTO;
import com.blog.app.Blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUserDto = this.userService.createUser(userDTO);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable("userId") Integer uid){
        UserDTO updatedUser = this.userService.updateUser(userDTO,uid);
        return ResponseEntity.ok(updatedUser);
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<> deleteUser(@PathVariable("userId") Integer uid){
//        this.userService.deleteUser(uid);
//        return new ResponseEntity<>(new ("User deleted Successfully",true), HttpStatus.OK);
//    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // GET - user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
