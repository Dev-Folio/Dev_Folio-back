package com.inhatc.dev_folio.main.controller;

import com.inhatc.dev_folio.project.controller.ProjectController;
import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final ProjectService projectService;

    @GetMapping("/")
    public List<ProjectDto.Card> main(@PageableDefault(size = 5) Pageable pageable) {
        log.info("main()");
        SearchDto.Detail searchDto = SearchDto.Detail.builder()
                .keyword("")
                .members(new ArrayList<>())
                .tags(new ArrayList<>())
                .build();
        return projectService.search(searchDto, pageable).toList();
    }
}
