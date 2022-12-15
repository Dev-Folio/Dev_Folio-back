package com.inhatc.dev_folio.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.inhatc.dev_folio.member.constant.Role;
import com.inhatc.dev_folio.member.entity.Member;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MemberDto {

    private String email;
    private String name;
    private String phone;
    private String password;
    private Role role;

    private static ModelMapper modelMapper = new ModelMapper();
    public static MemberDto of(Member member){return modelMapper.map(member, MemberDto.class);}

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

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProfile {
        private String image;
        private String name;
        private String email;
        private String info;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SetProfile {
        private String image;
        private String info;
    }

}
