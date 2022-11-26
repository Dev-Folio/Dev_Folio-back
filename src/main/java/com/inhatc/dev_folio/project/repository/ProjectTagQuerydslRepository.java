package com.inhatc.dev_folio.project.repository;

import java.util.List;

import com.inhatc.dev_folio.project.entity.Project;

public interface ProjectTagQuerydslRepository {
    public List<Project> findByTagIds(List<Long> tagIds);
}
