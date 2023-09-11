package com.exam.examserver.service;

import com.exam.examserver.entity.exam.Category;

import java.util.Set;

public interface CategotyService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategory();
    public  Category getCategory(Long cid);
    public void deleteCategory(Long cid);

}
