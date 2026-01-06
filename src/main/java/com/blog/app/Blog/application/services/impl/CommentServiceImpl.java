package com.blog.app.Blog.application.services.impl;

import com.blog.app.Blog.application.entites.Comment;
import com.blog.app.Blog.application.entites.Post;
import com.blog.app.Blog.application.exceptions.ResourceNotFoundException;
import com.blog.app.Blog.application.paylods.CommentDto;
import com.blog.app.Blog.application.repository.CommentRepo;
import com.blog.app.Blog.application.repository.PostRepo;
import com.blog.app.Blog.application.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment SavedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(SavedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(com);

    }
}
