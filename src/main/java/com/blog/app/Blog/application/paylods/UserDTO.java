package com.blog.app.Blog.application.paylods;

import com.blog.app.Blog.application.entites.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    private Set<RoleDto> roles = new HashSet<>();

}
