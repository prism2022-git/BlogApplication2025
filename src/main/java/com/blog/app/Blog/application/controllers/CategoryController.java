package com.blog.app.Blog.application.controllers;

import com.blog.app.Blog.application.paylods.ApiResponse;
import com.blog.app.Blog.application.paylods.CategoryDTO;
import com.blog.app.Blog.application.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createUser(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createCategoryDto = this.categoryService.createCategory(categoryDTO);
        return  new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDTO> updateUser(@RequestBody CategoryDTO categoryDTO,@PathVariable("catId") Integer catId){
        CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO,catId);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("catId") Integer catId){
        this.categoryService.deleteCategory(catId);
        // return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted successfully",true),HttpStatus.OK);
        return new ResponseEntity(Map.of("Message","Category deleted Successfully"),HttpStatus.OK);
    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){

        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    // GET - user get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getSingleUser(@PathVariable Integer catId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(catId));
    }
}
