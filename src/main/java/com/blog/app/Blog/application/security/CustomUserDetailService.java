package com.blog.app.Blog.application.security;

import com.blog.app.Blog.application.entites.User;
import com.blog.app.Blog.application.exceptions.ResourceNotFoundException;
import com.blog.app.Blog.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from dataBase by userName;
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User","email : " +username,0));

        return user;
    }
}
