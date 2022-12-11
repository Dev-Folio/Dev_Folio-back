package com.inhatc.dev_folio.project.controller;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/project/{projectId}")
    public ProjectDto.Detail getProject(@PathVariable Long projectId) {
        log.info("getProject(projectId:{})", projectId.toString());
        return projectService.getProject(projectId);
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

    @DeleteMapping("/project/{projectId}")
    public void deleteProject(@PathVariable Long projectId){
        log.info("deleteProject(projectId:{})", projectId);
        projectService.deleteProject(projectId);
    }

    /**
     * 회원의 좋아요 여부 조회
     */
    @GetMapping("/project/{projectId}/like")
    public ProjectDto.Like getLike(@PathVariable Long projectId, Principal principal) {
        log.info("getLike(projectId:{}, email:{})", projectId.toString(), principal.getName());
        return projectService.getLike(projectId, principal.getName());
    }

    /**
     * 좋아요 클릭
     */
    @PostMapping("/project/{projectId}/like")
    public ProjectDto.Like postLike(@PathVariable Long projectId, Principal principal) {
        log.info("clickLike(projectId:{}, email:{})", projectId.toString(), principal.getName());
        return projectService.clickLike(projectId, principal.getName());
    }
}
