package com.inhatc.dev_folio.project.service;

import com.inhatc.dev_folio.category.entity.Comment;
import com.inhatc.dev_folio.constant.ErrorMessage;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import com.inhatc.dev_folio.project.dto.CommentDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.mapper.CommentMapper;
import com.inhatc.dev_folio.project.repository.CommentRepository;
import com.inhatc.dev_folio.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public List<CommentDto.View> getComment(Long projectId, String email) {
        List<Comment> comments = commentRepository.findByProjectId(projectId);

        // 사용자가 로그인상태가 아닐 때
        if (email == null) {
            return CommentMapper.INSTANCE.commentListToViewList(comments);
        }

        // 로그인 상태일 때
        else {
            Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_EMAIL_NOT_FOUND.getMessage()));
            List<CommentDto.View> views = new ArrayList<>();

            CommentMapper commentMapper = CommentMapper.INSTANCE;
            for (Comment comment : comments) {
                CommentDto.View view = commentMapper.commentToView(comment);
                view.setSelf(comment.getMember().equals(member));
                views.add(view);
            }
            return views;
        }
    }

    public void writeComment(Long projectId, CommentDto.Contents contents, String email) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_EMAIL_NOT_FOUND.getMessage()));
        Comment newComment = Comment.builder()
                .contents(contents.getContents())
                .member(member)
                .project(project)
                .build();
        commentRepository.save(newComment);
    }

    public void updateComment(Long commentId, CommentDto.Contents contents, String email) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage()));
        // 댓글이 삭제됐는지 확인
        if (comment.isDeleted()) {
            throw new IllegalStateException(ErrorMessage.COMMENT_ALREADY_DELETED.getMessage());
        }
        // 권한 있는지 확인
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_EMAIL_NOT_FOUND.getMessage()));
        if (comment.getMember() != member) {
            throw new IllegalStateException(ErrorMessage.COMMENT_ACCESS_DENIED.getMessage());
        }
        comment.updateContents(contents.getContents());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage()));
        // 작성자인지 확인
        if (!comment.getMember().getEmail().equals(email)) {
            throw new IllegalStateException(ErrorMessage.COMMENT_ACCESS_DENIED.getMessage());
        }
        commentRepository.deleteById(commentId);
    }
}
