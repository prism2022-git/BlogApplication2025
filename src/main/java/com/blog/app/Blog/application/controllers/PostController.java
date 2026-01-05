package com.blog.app.Blog.application.controllers;

import com.blog.app.Blog.application.config.AppConstants;
import com.blog.app.Blog.application.paylods.ApiResponse;
import com.blog.app.Blog.application.paylods.PostDTO;
import com.blog.app.Blog.application.paylods.PostResponse;
import com.blog.app.Blog.application.services.FileService;
import com.blog.app.Blog.application.services.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){

      PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
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

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
       List<PostDTO> result= this.postService.searchPosts(keywords);
       return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);

    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(

            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
    ) throws IOException {

        PostDTO postDTO = this.postService.getPostById(null);
        String fileName=this.fileService.uploadImage(path,image);

        postDTO.setImageName(fileName);
        PostDTO updatePost=this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
    }

    // method to serve files

    @GetMapping(value= "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
