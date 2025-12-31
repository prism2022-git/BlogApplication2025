package com.blog.app.Blog.application.repository;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.entites.Post;
import com.blog.app.Blog.application.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("select p from Post p where p.title like :key")
    List<Post> searchBytitle(@Param("key") String title);
}
