package com.inhatc.dev_folio.project.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectTag;
import com.inhatc.dev_folio.project.entity.Tag;

@SpringBootTest
public class ProjectTagRepositoryTest {

    @Autowired
    ProjectTagRepository projectTagRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TagRepository tagRepository;

    @DisplayName("예시 데이터 삽입")
    @Test
    void saveProjectTag() {
        List<Project> projects = projectRepository.findAll();
        Tag tag = tagRepository.findById(11L).get();

        for (Project project : projects) {
            ProjectTag projectTag = ProjectTag.builder().project(project).tag(tag).build();
            projectTagRepository.save(projectTag);
        }
    }

}
