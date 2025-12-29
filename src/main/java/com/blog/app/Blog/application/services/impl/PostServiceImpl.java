package com.blog.app.Blog.application.services.impl;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.entites.Post;
import com.blog.app.Blog.application.entites.User;
import com.blog.app.Blog.application.exceptions.ResourceNotFoundException;
import com.blog.app.Blog.application.paylods.PostDTO;
import com.blog.app.Blog.application.paylods.PostResponse;
import com.blog.app.Blog.application.repository.CategoryRepo;
import com.blog.app.Blog.application.repository.PostRepo;
import com.blog.app.Blog.application.repository.UserRepo;
import com.blog.app.Blog.application.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        Post post = this.modelMapper.map(postDTO,Post.class);
        post.setImageName("default.png");
        post.setPostAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return  this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","PostId",postId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","PostId",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize) {
        /*int pageSize =5;
        int pageNo = 1;*/
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPost = pagePost.getContent();

          List<PostDTO> postDto = allPost.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
            PostResponse postResponse = new PostResponse();
            postResponse.setContent(postDto);
            postResponse.setPageNumber(pagePost.getNumber());
            postResponse.setPageSize(pagePost.getSize());
            postResponse.setTotalElements(pagePost.getNumberOfElements());
            postResponse.setTotalPages(pagePost.getTotalPages());
            postResponse.setLastPage(pagePost.isLast());
            postResponse.setPrevPage(pagePost.isFirst());
          return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","PostId",postId));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDTO> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDTO> userPost = posts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return userPost;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }
}
