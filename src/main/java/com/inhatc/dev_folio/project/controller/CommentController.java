package com.inhatc.dev_folio.project.controller;

import com.inhatc.dev_folio.project.dto.CommentDto;
import com.inhatc.dev_folio.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/project/{projectId}/comment")
    public List<CommentDto.View> getComment(@PathVariable Long projectId, Principal principal) {
        if (principal == null) {
            log.info("getComment(id:{}, email:{})", projectId.toString(), null);
            return commentService.getComment(projectId, null);
        } else {
            log.info("getComment(id:{}, email:{})", projectId.toString(), principal.getName());
            return commentService.getComment(projectId, principal.getName());
        }
    }

    @PostMapping("/project/{projectId}/comment")
    public void writeComment(@PathVariable Long projectId, @RequestBody CommentDto.Contents contents, Principal principal) {
        log.info("writeComment(projectId:{}, contents:{}, email:{})", projectId.toString(), contents.getContents(), principal.getName());
        commentService.writeComment(projectId, contents, principal.getName());
    }

    @PutMapping("/project/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentDto.Contents contents, Principal principal) {
        log.info("updateComment(commentId:{}, contents:{}, email:{})", commentId.toString(), contents.getContents(), principal.getName());
        commentService.updateComment(commentId, contents, principal.getName());
    }

    @DeleteMapping("/project/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId, Principal principal) {
        log.info("updateComment(commentId:{}, email:{})", commentId.toString(), principal.getName());
        commentService.deleteComment(commentId, principal.getName());
    }
}
