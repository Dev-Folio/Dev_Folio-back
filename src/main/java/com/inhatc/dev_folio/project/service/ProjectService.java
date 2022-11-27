package com.inhatc.dev_folio.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.mapper.ProjectMapper;
import com.inhatc.dev_folio.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectDto.Projects search(SearchDto.Detail searchDto, Pageable pageable) {

        List<Project> projects = projectRepository.search(searchDto, pageable);
        List<ProjectDto.Card> cards = ProjectMapper.INSTANCE.projectListToCardList(projects);
        return ProjectDto.Projects.builder().projects(cards).build();
    }

}
