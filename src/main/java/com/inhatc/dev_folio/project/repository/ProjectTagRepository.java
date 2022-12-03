package com.inhatc.dev_folio.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectTag;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {
}
