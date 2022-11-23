package com.inhatc.dev_folio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.dev_folio.dto.ProjectDto;
import com.inhatc.dev_folio.dto.SearchDto;
import com.inhatc.dev_folio.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final ProjectService projectService;

    @GetMapping("/search")
    public List<ProjectDto> search(@RequestBody SearchDto searchDto) {
        log.info(searchDto.toString());
        List<ProjectDto> list = projectService.getSearchData();
        return list;
    }
}
