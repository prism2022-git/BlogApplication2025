package com.blog.app.Blog.application.services;

import com.blog.app.Blog.application.paylods.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer CommentId);

}
