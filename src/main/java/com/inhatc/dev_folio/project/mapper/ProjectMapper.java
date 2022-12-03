package com.inhatc.dev_folio.project.mapper;

import java.util.List;

import com.inhatc.dev_folio.category.entity.CategoryTag;
import com.inhatc.dev_folio.project.entity.GithubUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.TagDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectTag;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "id", target = "projectId")
    @Mapping(source = "name", target = "projectName")
    @Mapping(source = "projectTags", target = "tags")
    ProjectDto.Card projectToCard(Project project);

    List<ProjectDto.Card> projectListToCardList(List<Project> projects);

    @Mapping(source = "tag.id", target = "tagId")
    @Mapping(source = "tag.name", target = "name")
    @Mapping(source = "tag.color", target = "color")
    @Mapping(target = "categories", ignore = true)
    TagDto projectTagToTagDto(ProjectTag projectTag);

    List<TagDto> projectTagListToTagDtoList(List<ProjectTag> projectTags);

    @Mapping(source = "id", target = "memberId")
    @Mapping(source = "profileImage.url", target = "image")
    MemberDto.Preview memberToPreview(Member member);

    @Mapping(target = "tags", source = "projectTags")
    @Mapping(target = "projectName", source = "name")
    @Mapping(target = "github", source = "githubUrls")
    ProjectDto.Project projectToProjectDto(Project project);

    static String getGithubUrl(GithubUrl githubUrl) {return githubUrl.getUrl();}
    List<String> githubUrlListToStringList(List<GithubUrl> githubUrls);
}
