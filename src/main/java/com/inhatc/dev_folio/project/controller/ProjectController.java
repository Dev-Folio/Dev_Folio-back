package com.inhatc.dev_folio.project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/search")
    public Page<ProjectDto.Card> search(
            @RequestBody SearchDto.Detail searchDto,
            @PageableDefault(size = 12) Pageable pageable) {
        log.info("searchDto >>> " + searchDto.toString());
        return projectService.search(searchDto, pageable);
    }

    @GetMapping("/project/{id}")
    public ProjectDto.Detail getProject(@PathVariable Long id) {
        log.info("getProject(id:{})", id.toString());
        return projectService.getProject(id);
    }

    @PostMapping("/project")
    public ProjectDto.ProjectId postProject(@RequestBody ProjectDto.ProjectForm projectForm){
        log.info("postProject(createProject:{})", projectForm.toString());
        return projectService.saveProject(projectForm);
    }

    @PutMapping("/project/{projectId}")
    public void putProject(@PathVariable Long projectId, @RequestBody ProjectDto.ProjectForm projectForm){
        log.info("putProject(projectId:{}, createProject:{})", projectId, projectForm.toString());
        projectService.updateProject(projectId, projectForm);
    }

    @GetMapping("/project/{id}/like")
    public ProjectDto.Like getLike(@PathVariable Long id) {
        log.info("getLike(id:{})", id.toString());
        return projectService.getLike(id);
    }

    @PostMapping("/project/{id}/like")
    public ProjectDto.Like postLike(@PathVariable Long id) {
        log.info("clickLike(id:{})", id.toString());
        return projectService.clickLike(id);
    }
}
