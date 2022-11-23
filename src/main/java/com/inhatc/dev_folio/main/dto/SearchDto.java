package com.inhatc.dev_folio.main.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.inhatc.dev_folio.member.constant.Order;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchDto {
    private List<Long> tags;
    private int page;
    private Order order;
    private String query;
    private Long memberId;
}
