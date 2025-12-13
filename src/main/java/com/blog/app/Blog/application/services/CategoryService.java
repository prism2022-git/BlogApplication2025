package com.blog.app.Blog.application.services;

import com.blog.app.Blog.application.paylods.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

     void deleteCategory(Integer categoryId);

     CategoryDTO getCategoryById(Integer categoryId);

    List<CategoryDTO> getCategories();


}
