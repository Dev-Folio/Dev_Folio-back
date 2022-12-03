package com.inhatc.dev_folio.project.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
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

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<ProjectDto.Card> search(SearchDto.Detail searchDto, Pageable pageable) {
        // Page<Project>를 Page<ProjectDto.Card>로 매핑
        Page<Project> projectPage = projectRepository.search(searchDto, pageable);
        List<Project> projects = projectPage.getContent();
        List<ProjectDto.Card> cards = ProjectMapper.INSTANCE.projectListToCardList(projects);
        return new PageImpl<>(cards, projectPage.getPageable(), projectPage.getTotalPages());
    }

    public ProjectDto.Project getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id의 프로젝트가 없습니다."));
        return ProjectMapper.INSTANCE.projectToProjectDto(project);
    }

    public ProjectDto.Like getLike(Long id) {
        // TODO: 회원가입 기능이 구현된 후 만들것.
        // Project project = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id의 프로젝트가 없습니다."));
        // return project.getLikes();
        return null;
    }

    public ProjectDto.Like clickLike(Long id) {
        // TODO: 회원가입 기능이 구현된 후 만들것.
        return null;
    }
}
