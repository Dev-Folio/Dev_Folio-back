package com.inhatc.dev_folio.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.dto.MemberDto.Preview;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProjectDto {

    // innerclass 장점 -> 여러가지의 DTO 형태를 하나의 클래스 안에서 구별할 수 있게 한다. DTO가 쓸데없이 만들어지는걸 방지.
    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class Projects {
        private List<Card> projects;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class Card {
        private Long projectId;
        private String thumbnail;
        private String projectName;
        private List<TagDto> tags;
        private MemberDto.Preview writedMember;
        private int views;
        private int likes;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Project {
        private String thumbnail;
        private String projectName;
        private MemberDto.Preview writedMember;
        private List<MemberDto.Preview> contributedMembers;
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
}
