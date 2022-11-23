package com.inhatc.dev_folio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.dev_folio.dto.ProjectDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProjectController {

    @GetMapping("/project/{id}")
    public ProjectDto.Project getProject(@PathVariable Long id) {
        log.info(id.toString());
        return new ProjectDto.Project();
    };
}
