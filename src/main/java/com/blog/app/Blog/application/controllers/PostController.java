package com.blog.app.Blog.application.controllers;

import com.blog.app.Blog.application.paylods.ApiResponse;
import com.blog.app.Blog.application.paylods.PostDTO;
import com.blog.app.Blog.application.paylods.PostResponse;
import com.blog.app.Blog.application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDTO createPost = this.postService.createPost(postDTO,userId,categoryId);
        return  new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
    }

    //get all post by User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(
            @PathVariable Integer userId
    ){
    List<PostDTO> posts = this.postService.getPostsByUser(userId);
    return  new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
    }

    //get all post by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDTO> posts = this.postService.getPostsByCategory(categoryId);
        return  new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
    }
    //Get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "10", required = false) Integer pageSize){
      PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    //Get post by Id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getAllPosts(@PathVariable Integer postId){
        PostDTO postid = this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(postid,HttpStatus.OK);
    }

    @DeleteMapping("/posts/postId")
    public ApiResponse deletePostById(@PathVariable Integer posId){
        this.postService.deletePost(posId);
        return new ApiResponse("Post is successFully deleted",true);
    }

    @PutMapping("/posts/postId")
    public  ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
                                               @PathVariable Integer posId){
        PostDTO updatePost = this.postService.updatePost(postDTO,posId);
        return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK) ;
    }
}
