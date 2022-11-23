package com.inhatc.dev_folio.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

public class MemberDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Preview {
        private Long memberId;
        private String name;
        private String image;
    }
}
