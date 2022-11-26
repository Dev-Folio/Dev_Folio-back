package com.inhatc.dev_folio.project.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.QProject;
import com.inhatc.dev_folio.project.entity.QProjectTag;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectQuerydslRepositoryImpl implements ProjectQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /*
     * SELECT * FROM project p
     * WHERE
     * 
     * project_id IN (
     * SELECT project_id
     * FROM project_tag
     * WHERE tag_id IN (11,12,13)
     * GROUP BY project_id
     * HAVING COUNT(project_id) > SIZE([11,12,13])
     * )
     * 
     * AND project_id IN (
     * SELECT project_id
     * FROM project_member
     * WHERE writed_member_id IN (1,2,3)
     * GROUP BY project_id
     * HAVING COUNT(project_id) > SIZE([1,2,3])
     * )
     * 
     * AND name LIKE "%프로젝트%"
     * 
     * ORDER BY created_date ASC
     */
    @Override
    public List<Project> search(SearchDto.Detail searchDto, Pageable pageable) {
        QProjectTag projectTag = QProjectTag.projectTag;
        QProject project = QProject.project;

        JPAQuery<Project> query = jpaQueryFactory.selectFrom(project);

        // tags가 주어졌을 경우
        if (!searchDto.getTags().isEmpty()) {
            query.where(project.id.in(
                    JPAExpressions.select(projectTag.project.id)
                            .from(projectTag)
                            .where(projectTag.tag.id.in(searchDto.getTags()))
                            .groupBy(projectTag.project.id)
                            .having(projectTag.project.id.count().goe(searchDto.getTags().size()))));
        }

        return query.fetch();
    }

}
