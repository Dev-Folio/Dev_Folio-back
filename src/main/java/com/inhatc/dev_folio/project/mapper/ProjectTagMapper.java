package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.project.dto.TagDto;
import com.inhatc.dev_folio.project.entity.ProjectTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectTagMapper {
    ProjectTagMapper INSTANCE = Mappers.getMapper(ProjectTagMapper.class);

    @Mapping(source = "tag.id", target = "tagId")
    @Mapping(source = "tag.name", target = "name")
    @Mapping(source = "tag.color", target = "color")
    @Mapping(target = "categories", ignore = true)
    TagDto projectTagToTagDto(ProjectTag projectTag);

    List<TagDto> projectTagListToTagDtoList(List<ProjectTag> projectTags);
}
