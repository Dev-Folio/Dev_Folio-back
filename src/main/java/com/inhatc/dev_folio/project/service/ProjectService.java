package com.inhatc.dev_folio.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.main.dto.SearchDto;
import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectDto.Projects getSearchData(SearchDto searchDto) {

        // Page<Project> projectPage = projectRepository;

        ProjectDto.Projects projectDtos = new ProjectDto.Projects();
        return projectDtos;
    }

}
