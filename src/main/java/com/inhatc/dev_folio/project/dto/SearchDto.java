package com.inhatc.dev_folio.project.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

public class SearchDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private List<Long> tags;
        private String keyword;
        private List<Long> members;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Fast {
        private String keyword;
        private List<Long> members;
    }
}
