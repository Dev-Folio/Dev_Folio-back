package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
