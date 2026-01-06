package com.blog.app.Blog.application.paylods;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.entites.Comment;
import com.blog.app.Blog.application.entites.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CommentDto> comments = new HashSet<>();


}
