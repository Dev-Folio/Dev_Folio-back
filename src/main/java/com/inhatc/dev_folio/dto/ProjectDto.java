package com.inhatc.dev_folio.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

public class ProjectDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Projects {
        private List<Card> projects;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Card {
        private Long projectId;
        private String thumbnail;
        private String projectName;
        private List<TagDto> tags;
        private String profileImage;
        private String profileName;
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
