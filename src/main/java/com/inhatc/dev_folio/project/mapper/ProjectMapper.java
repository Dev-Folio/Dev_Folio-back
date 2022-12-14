package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.member.mapper.MemberMapper;
import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper extends MemberMapper, LikeMapper, ProjectMemberMapper, ProjectTagMapper, GithubUrlMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "id", target = "projectId")
    @Mapping(source = "name", target = "projectName")
    @Mapping(source = "projectTags", target = "tags")
    @Mapping(target = "likes", expression = "java(project.getLikes().size())")
    @Mapping(target = "comments", expression = "java(project.getComments().size())")
    ProjectDto.Card projectToCard(Project project);

    List<ProjectDto.Card> projectListToCardList(List<Project> projects);

    @Mapping(target = "tags", source = "projectTags")
    @Mapping(target = "projectName", source = "name")
    @Mapping(target = "github", source = "githubUrls")
    @Mapping(target = "likes", expression = "java(project.getLikes().size())")
    ProjectDto.Detail projectToDetail(Project project);
}
