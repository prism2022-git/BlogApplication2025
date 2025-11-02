package com.blog.app.Blog.application.services.impl;

import com.blog.app.Blog.application.entites.User;
import com.blog.app.Blog.application.exceptions.ResourceNotFoundException;
import com.blog.app.Blog.application.paylods.UserDTO;
import com.blog.app.Blog.application.repository.UserRepo;
import com.blog.app.Blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.dtoToUser(userDTO);
        User saveduser = this.userRepo.save(user);
        return this.userToDTO(saveduser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userID) {
        User user = this.userRepo.findById(userID).
                orElseThrow(()-> new ResourceNotFoundException("User","id",userID));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDTO userDto1 = this.userToDTO(updatedUser);
        return userDto1;
    }

    @Override
    public UserDTO getUserById(Integer userID) {

        User user = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","id",userID));

        return this.userToDTO((user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();

       List<UserDTO>  usersDto = users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public void deleteUser(Integer userID) {
        User user = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","Id",userID));
       this.userRepo.delete(user);
    }

    // DTO to User conversion
    private User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return  user;
    }

    // User to DTO
    private UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setAbout(user.getAbout());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
