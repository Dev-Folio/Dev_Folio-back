package com.inhatc.dev_folio.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;

public interface QProjectRepository {
    Page<Project> search(SearchDto.Detail searchDto, Pageable pageable);
}
