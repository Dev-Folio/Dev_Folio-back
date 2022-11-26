package com.inhatc.dev_folio.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.dev_folio.main.dto.SearchDto;
import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final ProjectService projectService;

    @GetMapping("/search")
    public ProjectDto.Projects search(@RequestBody SearchDto searchDto) {
        log.info(searchDto.toString());
        ProjectDto.Projects projects = projectService.getSearchData(searchDto);
        return projects;
    }
}
