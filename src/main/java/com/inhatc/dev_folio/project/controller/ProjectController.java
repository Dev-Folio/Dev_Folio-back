package com.inhatc.dev_folio.project.controller;

import com.inhatc.dev_folio.project.dto.CommentDto;
import com.inhatc.dev_folio.project.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;
    private final CommentService commentService;

    @GetMapping("/search")
    public Page<ProjectDto.Card> search(
            @RequestBody SearchDto.Detail searchDto,
            @PageableDefault(size = 12) Pageable pageable) {
        log.info("searchDto >>> " + searchDto.toString());
        return projectService.search(searchDto, pageable);
    }

    @GetMapping("/project/{id}")
    public ProjectDto.Project getProject(@PathVariable Long id) {
        log.info("getProject(id:{})", id.toString());
        return projectService.getProject(id);
    }

    @GetMapping("/project/{id}/like")
    public ProjectDto.Like getLike(@PathVariable Long id) {
        log.info("getLike(id:{})", id.toString());
        return projectService.getLike(id);
    }

    @PostMapping("/project/{id}/like")
    public ProjectDto.Like clickLike(@PathVariable Long id) {
        log.info("clickLike(id:{})", id.toString());
        return projectService.clickLike(id);
    }

    @GetMapping("/project/{id}/comment")
    public List<CommentDto.View> getComment(@PathVariable Long id){
        log.info("getComment(id:{})", id.toString());
        return commentService.getComment(id);
    }

    @PostMapping("/project/{projectId}/comment")
    public void writeComment(@PathVariable Long projectId, CommentDto.Contents contents){
        log.info("writeComment(projectId:{})", projectId.toString());
        commentService.writeComment(projectId, contents);
    }

    @PutMapping("/project/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, CommentDto.Contents commentDto){
        log.info("updateComment(commentId:{})", commentId.toString());
        commentService.updateComment(commentId, commentDto);
    }
}
