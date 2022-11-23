package com.inhatc.dev_folio.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.category.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
