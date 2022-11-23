package com.inhatc.dev_folio.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
