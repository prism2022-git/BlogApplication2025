package com.blog.app.Blog.application.services;

import com.blog.app.Blog.application.entites.Post;
import com.blog.app.Blog.application.paylods.PostDTO;
import com.blog.app.Blog.application.paylods.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    //create
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    //update
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(int pageNumber, int pageSize);

    //get single post
    PostDTO getPostById(Integer postId);

    //get all post by Category
    List<PostDTO> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDTO> getPostsByUser(Integer userId);

    //serach posts
    List<Post> searchPosts(String keyword);


}
