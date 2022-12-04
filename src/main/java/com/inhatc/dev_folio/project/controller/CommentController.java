package com.inhatc.dev_folio.project.controller;

import com.inhatc.dev_folio.project.dto.CommentDto;
import com.inhatc.dev_folio.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

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

    @DeleteMapping("/project/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        log.info("updateComment(commentId:{})", commentId.toString());
        commentService.deleteComment(commentId);
    }
}
