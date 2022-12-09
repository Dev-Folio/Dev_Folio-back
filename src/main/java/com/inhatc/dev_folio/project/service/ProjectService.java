package com.inhatc.dev_folio.project.service;

import java.util.List;

import com.inhatc.dev_folio.constant.ErrorMessage;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import com.inhatc.dev_folio.project.entity.*;
import com.inhatc.dev_folio.project.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.project.dto.ProjectDto;
import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.mapper.ProjectMapper;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ProjectTagRepository projectTagRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final GithubUrlRepository githubUrlRepository;

    public Page<ProjectDto.Card> search(SearchDto.Detail searchDto, Pageable pageable) {
        // Page<Project>를 Page<ProjectDto.Card>로 매핑
        Page<Project> projectPage = projectRepository.search(searchDto, pageable);
        List<Project> projects = projectPage.getContent();
        List<ProjectDto.Card> cards = ProjectMapper.INSTANCE.projectListToCardList(projects);
        return new PageImpl<>(cards, projectPage.getPageable(), projectPage.getTotalPages());
    }

    public ProjectDto.Detail getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()));
        return ProjectMapper.INSTANCE.projectToDetail(project);
    }

    public ProjectDto.Like getLike(Long id) {
        // TODO: 회원가입 기능이 구현된 후 만들것.
        // Project project = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id의 프로젝트가 없습니다."));
        // return project.getLikes();
        return null;
    }

    public ProjectDto.Like clickLike(Long id) {
        // TODO: 회원가입 기능이 구현된 후 만들것.
        return null;
    }

    public ProjectDto.ProjectId saveProject(ProjectDto.ProjectForm projectForm) {
        // TODO: 로그인 기능 구현되면 wroteMember 채우기
        Member wroteMember = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));

        List<Member> contributedMembers = memberRepository.findAllById(projectForm.getContributedMembers());
        List<Tag> tags = tagRepository.findAllById(projectForm.getTags());

        Project project = Project.builder()
                .thumbnail(projectForm.getThumbnail())
                .name(projectForm.getProjectName())
                .startDate(projectForm.getStartDate())
                .endDate(projectForm.getEndDate())
                .detail(projectForm.getDetail())
                .contents(projectForm.getContents())
                .wroteMember(wroteMember)
                .build();
        projectRepository.save(project);

        for (Tag tag : tags) {
            ProjectTag projectTag = ProjectTag.builder()
                    .project(project)
                    .tag(tag)
                    .build();
            projectTagRepository.save(projectTag);
        }

        for (Member contributedMember : contributedMembers) {
            ProjectMember projectMember = ProjectMember.builder()
                    .project(project)
                    .member(contributedMember)
                    .build();
            projectMemberRepository.save(projectMember);
        }

        for (String github : projectForm.getGithub()){
            GithubUrl githubUrl = GithubUrl.builder()
                    .project(project)
                    .url(github)
                    .build();
            githubUrlRepository.save(githubUrl);
        }

        return ProjectDto.ProjectId.builder().projectId(project.getId()).build();
    }

    public void updateProject(Long projectId, ProjectDto.ProjectForm projectForm) {
        Member wroteMember = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()));

        if (!project.getWroteMember().equals(wroteMember)){
            throw new IllegalStateException(ErrorMessage.PROJECT_ACCESS_DENIED.getMessage());
        }

        project.updateProject(projectForm);
        projectRepository.save(project);

        // 원래 있던 projectTags를 지운다.
        List<ProjectTag> projectTags = project.getProjectTags();
        projectTagRepository.deleteAll(projectTags);

        // 그리고 새로 추가
        List<Tag> tags = tagRepository.findAllById(projectForm.getTags());
        for (Tag tag : tags) {
            ProjectTag projectTag = ProjectTag.builder()
                    .project(project)
                    .tag(tag)
                    .build();
            projectTagRepository.save(projectTag);
        }

        // 원래 있던 projectMember를 지운다
        List<ProjectMember> projectMembers = project.getContributedMembers();
        projectMemberRepository.deleteAll(projectMembers);

        // 그리고 새로 추가
        List<Member> contributedMembers = memberRepository.findAllById(projectForm.getContributedMembers());
        for (Member contributedMember : contributedMembers) {
            ProjectMember projectMember = ProjectMember.builder()
                    .project(project)
                    .member(contributedMember)
                    .build();
            projectMemberRepository.save(projectMember);
        }

        // 원래 있던 githubUrl을 지운다.
        List<GithubUrl> githubUrls = project.getGithubUrls();
        githubUrlRepository.deleteAll(githubUrls);

        // 그리고 새로 추가
        for (String github : projectForm.getGithub()){
            GithubUrl githubUrl = GithubUrl.builder()
                    .project(project)
                    .url(github)
                    .build();
            githubUrlRepository.save(githubUrl);
        }

    }

    public void deleteProject(Long projectId) {
        // TODO: 로그인 기능 구현 후 요청한 유저 가져오기
        Member requestMember = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()));
        if (project.getWroteMember().equals(requestMember)){
            projectRepository.deleteById(projectId);
        } else {
            throw new IllegalStateException(ErrorMessage.PROJECT_ACCESS_DENIED.getMessage());
        }
    }
}
