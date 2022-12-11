package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.project.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMemberMapper {
    ProjectMemberMapper INSTANCE = Mappers.getMapper(ProjectMemberMapper.class);

    @Mapping(target = "number", expression = "java(projectMember.getMember().getEmail().substring(0, projectMember.getMember().getEmail().indexOf('@')))")
    @Mapping(target = "name", source = "member.name")
    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "image", source = "member.profileImage.url")
    MemberDto.View projectMemberToView(ProjectMember projectMember);
}
