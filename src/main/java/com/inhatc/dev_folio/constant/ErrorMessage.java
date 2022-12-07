package com.inhatc.dev_folio.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    // Project
    PROJECT_NOT_FOUND("해당 id의 프로젝트가 없습니다."),
    PROJECT_ACCESS_DENIED("해당 프로젝트의 권한이 없습니다."),
    PROJECT_BAD_SEARCH_PARAMETER("검색 파라미터 잘못됨"),

    // Member
    MEMBER_NOT_FOUND("해당 id의 회원이 없습니다."),
    MEMBER_ALREADY_REGISTERED("이미 가입된 회원입니다."),

    // Comment
    COMMENT_NOT_FOUND("해당 id의 댓글이 없습니다."),
    COMMENT_ALREADY_DELETED("이미 삭제된 댓글입니다.");

    private final String message;
}
