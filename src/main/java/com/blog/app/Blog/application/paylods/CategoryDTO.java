package com.blog.app.Blog.application.paylods;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 4,message = "Category Title can not be left empty")
    private String categoryTitle;

    @NotEmpty
    @Size(min=4,message = "Please provide the description of your Category")
    private String categoryDescription;
}
