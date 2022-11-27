package com.inhatc.dev_folio.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.inhatc.dev_folio.category.entity.CategoryTag;
import com.inhatc.dev_folio.project.dto.TagDto;
import com.inhatc.dev_folio.project.entity.Tag;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "id", target = "tagId")
    @Mapping(source = "categoryTags", target = "categories")
    TagDto tagToTagDto(Tag tag);

    List<TagDto> tagListToTagDtoList(List<Tag> tags);

    static Long getCategoryIds(CategoryTag categoryTag) {
        return categoryTag.getCategory().getId();
    }

}
