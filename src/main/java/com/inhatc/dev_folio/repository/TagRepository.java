package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
