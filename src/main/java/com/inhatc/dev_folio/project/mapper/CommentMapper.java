package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.category.entity.Comment;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.project.dto.CommentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "self", ignore = true)
    @Mapping(target = "profileImage", source = "member.profileImage.url")
    @Mapping(target = "name", source = "member.name")
    @Mapping(target = "commentId", source = "id")
    CommentDto.View commentToView(Comment comment);
}
