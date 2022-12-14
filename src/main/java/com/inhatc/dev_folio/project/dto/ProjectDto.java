package com.inhatc.dev_folio.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.inhatc.dev_folio.member.dto.MemberDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProjectDto {

    // innerclass 장점 -> 여러가지의 DTO 형태를 하나의 클래스 안에서 구별할 수 있게 한다. DTO가 쓸데없이 만들어지는걸 방지.
    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class Card {
        private Long projectId;
        private String thumbnail;
        private String projectName;
        private List<TagDto> tags;
        private MemberDto.View wroteMember;
        private int likes;
        private int comments;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class Detail {
        private String thumbnail;
        private String projectName;
        private MemberDto.View wroteMember;
        private List<MemberDto.View> contributedMembers;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<TagDto> tags;
        private List<String> github;
        private String detail;
        private String contents;
        private int views;
        private int likes;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class Like {
        private boolean like;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class ProjectId {
        private Long projectId;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class ProjectForm {
        private String thumbnail;
        private String projectName;
        private List<Long> contributedMembers;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<Long> tags;
        private List<String> github;
        private String detail;
        private String contents;
    }

}
