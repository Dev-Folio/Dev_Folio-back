package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.category.entity.Comment;
import com.inhatc.dev_folio.project.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "self", expression = "java(false)")
    @Mapping(target = "profileImage", source = "member.profileImage.url")
    @Mapping(target = "name", source = "member.name")
    @Mapping(target = "commentId", source = "id")
    CommentDto.View commentToView(Comment comment);

    List<CommentDto.View> commentListToViewList(List<Comment> comments);
}
