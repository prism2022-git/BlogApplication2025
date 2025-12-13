package com.blog.app.Blog.application.paylods;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min=4, message = "User name must be minimum if 4 characters")
    private  String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty(message = "Can not be left blank")
    @Size(min=4)
    //@Pattern(regexp = )
    private String password;

    @NotEmpty
    private String about;
}
