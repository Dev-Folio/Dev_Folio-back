package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}