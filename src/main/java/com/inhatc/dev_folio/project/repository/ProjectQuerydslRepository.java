package com.inhatc.dev_folio.project.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;

public interface ProjectQuerydslRepository {
    List<Project> search(SearchDto.Detail searchDto, Pageable pageable);
}
