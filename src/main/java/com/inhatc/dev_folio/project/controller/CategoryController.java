package com.inhatc.dev_folio.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.dev_folio.project.dto.CategoryDto;
import com.inhatc.dev_folio.project.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDto> getCategories() {
        log.info("getTags()");
        return categoryService.getCategories();
    }
}
