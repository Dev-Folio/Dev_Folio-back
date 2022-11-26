package com.inhatc.dev_folio.project.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.inhatc.dev_folio.member.constant.Order;

import lombok.Getter;
import lombok.ToString;

public class SearchDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Detail {
        private List<Long> tags;
        private String query;
        private List<Long> members;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Fast {
        private String query;
        private Long memberId;
    }
}
