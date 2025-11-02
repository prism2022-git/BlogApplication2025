package com.blog.app.Blog.application.repository;

import com.blog.app.Blog.application.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {


}
