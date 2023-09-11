package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.service.CategotyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategotyService categotyService;
    Category category;

    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category category1 = this.categotyService.addCategory(category);
        return ResponseEntity.ok(category1);
    }
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable ("categoryId") Long cid){
        return this.categotyService.getCategory(cid);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
    return  ResponseEntity.ok(this.categotyService.getCategory());
    }

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categotyService.updateCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable ("categoryId") Long cid){
        this.categotyService.deleteCategory(cid);
        return "the id has been deleted";
    }
}

