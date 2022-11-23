package com.inhatc.dev_folio.project.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
// jackson이라는 라이브러리.자바랑 json 쓰는 거 알아서 맞춰주는 라이브러리(tagId -> tag_id로 지가 맞춰줌)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TagDto {
    private Long tagId;
    private String name;
    private String color;
}
