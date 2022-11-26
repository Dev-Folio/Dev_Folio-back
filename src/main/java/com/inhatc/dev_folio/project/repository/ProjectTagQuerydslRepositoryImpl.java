package com.inhatc.dev_folio.project.repository;

import java.util.List;

import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.QProjectTag;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ProjectTagQuerydslRepositoryImpl implements ProjectTagQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // SELECT project_id FROM project_tag
    // WHERE tag_id in :tagIds
    // GROUP BY project_id
    // HAVING count(project_id) > 1;
    public List<Project> findByTagIds(List<Long> tagIds) {
        QProjectTag projectTag = QProjectTag.projectTag;

        List<Project> a = jpaQueryFactory
                .select(projectTag.project)
                .from(projectTag)
                .where(projectTag.tag.id.in(tagIds))
                .groupBy(projectTag.project)
                .having(projectTag.project.count().gt(1))
                .fetch();

        return a;
    }
}
