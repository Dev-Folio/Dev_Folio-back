package com.inhatc.dev_folio.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.category.entity.Category;
import com.inhatc.dev_folio.category.repository.CategoryRepository;
import com.inhatc.dev_folio.project.dto.CategoryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return null;
    }

}
