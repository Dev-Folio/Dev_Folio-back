package com.inhatc.dev_folio.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

public class MemberDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public static class View {
        private Long memberId;
        private String name;
        private String image;
        private String number;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Search {
        private String query;
    }
}
