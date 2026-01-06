package com.blog.app.Blog.application.repository;

import com.blog.app.Blog.application.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
