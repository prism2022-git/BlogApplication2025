package com.blog.app.Blog.application.paylods;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String email;
    private String password;
}
