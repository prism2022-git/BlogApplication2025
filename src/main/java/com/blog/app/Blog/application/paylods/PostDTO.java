package com.blog.app.Blog.application.paylods;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.entites.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PostDTO {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDTO user;



}
