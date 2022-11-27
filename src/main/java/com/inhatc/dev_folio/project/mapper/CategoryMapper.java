package com.inhatc.dev_folio.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.inhatc.dev_folio.category.entity.Category;
import com.inhatc.dev_folio.category.entity.CategoryTag;
import com.inhatc.dev_folio.project.dto.CategoryDto;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "categoryTags", target = "tags")
    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoryListToCategoryDtoList(List<Category> categories);

    static Long getTagIds(CategoryTag categoryTag) {
        return categoryTag.getTag().getId();
    }
}
