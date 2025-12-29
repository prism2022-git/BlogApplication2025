package com.blog.app.Blog.application.repository;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.entites.Post;
import com.blog.app.Blog.application.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
