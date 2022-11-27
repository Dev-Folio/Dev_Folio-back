package com.inhatc.dev_folio.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.mapper.ProjectMapper;
import com.inhatc.dev_folio.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<ProjectDto.Card> search(SearchDto.Detail searchDto, Pageable pageable) {
        // Page<Project>를 Page<ProjectDto.Card>로 매핑
        Page<Project> projectPage = projectRepository.search(searchDto, pageable);
        List<Project> projects = projectPage.getContent();
        List<ProjectDto.Card> cards = ProjectMapper.INSTANCE.projectListToCardList(projects);
        return new PageImpl<>(cards, projectPage.getPageable(), projectPage.getTotalPages());
    }

}
