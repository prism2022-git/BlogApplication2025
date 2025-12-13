package com.blog.app.Blog.application.services.impl;

import com.blog.app.Blog.application.entites.Category;
import com.blog.app.Blog.application.exceptions.ResourceNotFoundException;
import com.blog.app.Blog.application.paylods.CategoryDTO;
import com.blog.app.Blog.application.paylods.UserDTO;
import com.blog.app.Blog.application.repository.CategoryRepo;
import com.blog.app.Blog.application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category cat = this.modelMapper.map(categoryDTO,Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        this.modelMapper.map(categoryDTO,categoryId);
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categeory","Category Id",categoryId));
        cat.setCategoryTitle(categoryDTO.getCategoryTitle());
        cat.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Category ID" ,categoryId));
        this.categoryRepo.delete(cat);
    }
    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Category ID" ,categoryId));
        return this.modelMapper.map(cat,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDTO>  categoryDTOs = categories.stream().map(cat -> this.modelMapper.map(cat,CategoryDTO.class)).collect(Collectors.toList());

        return categoryDTOs;
    }
}
