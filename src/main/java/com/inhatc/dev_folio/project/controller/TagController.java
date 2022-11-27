package com.inhatc.dev_folio.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.dev_folio.project.dto.TagDto;
import com.inhatc.dev_folio.project.service.TagService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;

    @GetMapping("/tag")
    public List<TagDto> getTags() {
        log.info("getTags()");
        return tagService.getTags();
    }
}
