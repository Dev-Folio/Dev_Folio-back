package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
