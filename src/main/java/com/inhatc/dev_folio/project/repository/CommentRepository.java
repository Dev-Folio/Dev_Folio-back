package com.inhatc.dev_folio.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.category.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProjectId(Long id);
}
