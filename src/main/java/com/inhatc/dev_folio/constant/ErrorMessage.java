package com.inhatc.dev_folio.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    // 인증
    INVALID_JWT("유효하지 않은 토큰입니다."),
    INVALID_MEMBER("비밀번호가 틀렸거나, 아이디가 존재하지 않습니다."),
    NOT_LOGIN("로그인되지 않았습니다."),
    FORBIDDEN("접근 권한이 없습니다."),

    // Project
    PROJECT_NOT_FOUND("해당 id의 프로젝트가 없습니다."),
    PROJECT_ACCESS_DENIED("해당 프로젝트의 권한이 없습니다."),
    PROJECT_BAD_SEARCH_PARAMETER("검색 파라미터 잘못됨"),

    // Member
    MEMBER_ID_NOT_FOUND("해당 id의 회원이 없습니다."),
    MEMBER_EMAIL_NOT_FOUND("해당 email의 회원이 없습니다."),
    MEMBER_ALREADY_REGISTERED("이미 가입된 회원입니다."),

    // Comment
    COMMENT_NOT_FOUND("해당 id의 댓글이 없습니다."),
    COMMENT_ALREADY_DELETED("이미 삭제된 댓글입니다."),
    COMMENT_ACCESS_DENIED("해당 댓글의 권한이 없습니다."),

    // Likes
    Likes_NOT_FOUND("해당하는 좋아요가 없습니다.");

    private final String message;
}
