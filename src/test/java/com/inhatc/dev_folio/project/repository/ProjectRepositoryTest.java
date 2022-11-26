package com.inhatc.dev_folio.project.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inhatc.dev_folio.project.entity.Project;

@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @DisplayName("예시 데이터 삽입")
    @Test
    void insertData() {
        saveProjects();
    }

    void saveProjects() {
        for (int i = 0; i < 10; i++) {
            Project project = Project.builder()
                    .thumbnail("testAwsURL")
                    .name("테스트 프로젝트" + i)
                    .startDate(LocalDate.now().plusDays(i))
                    .endDate(LocalDate.now().plusDays(i + 1))
                    .detail("테스트 프로젝트의 detail")
                    .contents("테스트 프로젝트의 contnets")
                    .build();
            projectRepository.save(project);
        }
    }
}
