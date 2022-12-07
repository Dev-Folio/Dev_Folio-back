package com.inhatc.dev_folio.member.mapper;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "number", expression = "java(member.getEmail().substring(0, member.getEmail().indexOf('@')))")
    @Mapping(source = "id", target = "memberId")
    @Mapping(source = "profileImage.url", target = "image")
    MemberDto.View memberToView(Member member);

    List<MemberDto.View> memberListToViewList(List<Member> members);

    @Mapping(target = "image", source = "profileImage.url")
    MemberDto.GetProfile memberToProfilePage(Member member);
}
