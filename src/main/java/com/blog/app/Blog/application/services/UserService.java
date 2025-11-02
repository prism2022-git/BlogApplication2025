package com.blog.app.Blog.application.services;

import com.blog.app.Blog.application.paylods.UserDTO;

import java.util.List;

public interface UserService {

    // create user
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer userID);
    UserDTO getUserById(Integer userID);          // this is to fetch the single user

    List<UserDTO> getAllUsers();   // to fetch all the users

    void deleteUser(Integer userID);
}
