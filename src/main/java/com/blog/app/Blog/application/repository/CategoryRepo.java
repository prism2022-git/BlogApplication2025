package com.blog.app.Blog.application.repository;

import com.blog.app.Blog.application.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CategoryRepo extends JpaRepository<Category,Integer> {

}
