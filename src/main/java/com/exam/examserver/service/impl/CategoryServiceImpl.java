package com.exam.examserver.service.impl;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.repo.CategoryRepository;
import com.exam.examserver.service.CategotyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategotyService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategory() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long cid) {
        return this.categoryRepository.findById(cid).get();
    }

    @Override
    public void deleteCategory(Long cid) {
        this.categoryRepository.deleteById(cid);

    }
}
