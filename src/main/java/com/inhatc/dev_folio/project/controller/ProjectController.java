package com.inhatc.dev_folio.project.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ProjectDto.Projects search(
            @RequestBody SearchDto.Detail searchDto,
            @PageableDefault(size = 12) Pageable pageable) {
        log.info("searchDto >>> " + searchDto.toString());
        return projectService.search(searchDto, pageable);
    }

    @GetMapping("/project/{id}")
    public ProjectDto.Project getProject(@PathVariable Long id) {
        log.info(id.toString());
        return new ProjectDto.Project();
    };
}
