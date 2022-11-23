package com.inhatc.dev_folio.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.project.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
