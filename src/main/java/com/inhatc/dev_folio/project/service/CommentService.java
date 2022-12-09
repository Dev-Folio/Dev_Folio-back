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

    public List<CommentDto.View> getComment(Long id) {
        List<Comment> comments = commentRepository.findByProjectId(id);

        // TODO: 로그인 기능 구현 후 현재 로그인 된 사용자로 교체
        Member member = memberRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));

        CommentMapper commentMapper = CommentMapper.INSTANCE;
        List<CommentDto.View> views = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto.View view = commentMapper.commentToView(comment);
            view.setSelf(comment.getMember().equals(member));
            views.add(view);
        }

        return views;
    }

    public void writeComment(Long projectId, CommentDto.Contents contents) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()));
        Comment newComment = Comment.builder()
                .contents(contents.getContents())
                // TODO: 로그인 기능이 완료되면 Member도 집어넣기
                // .member()
                .project(project)
                .build();
        commentRepository.save(newComment);
    }

    public void updateComment(Long commentId, CommentDto.Contents contents) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage()));
        if (comment.isDeleted()) {
            throw new IllegalStateException(ErrorMessage.COMMENT_ALREADY_DELETED.getMessage());
        }
        // TODO 로그인된 사용자 판별해서 수정 권한 있는지 확인하기
        comment.updateContents(contents.getContents());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        // TODO 로그인된 사용자 판별해서 삭제 권한 있는지 확인하기
        commentRepository.deleteById(commentId);
    }
}
