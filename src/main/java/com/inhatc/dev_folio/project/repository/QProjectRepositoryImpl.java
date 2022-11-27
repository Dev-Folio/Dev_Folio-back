package com.inhatc.dev_folio.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import com.inhatc.dev_folio.project.dto.SearchDto;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.QProject;
import com.inhatc.dev_folio.project.entity.QProjectMember;
import com.inhatc.dev_folio.project.entity.QProjectTag;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QProjectRepositoryImpl implements QProjectRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private CustomJPAQuery<Project> customProjectJPAQuery = new CustomJPAQuery<>();
    private CustomJPAQuery<Long> customLongJPAQuery = new CustomJPAQuery<>();

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
    public Page<Project> search(SearchDto.Detail searchDto, Pageable pageable) {
        QProject project = QProject.project;

        JPAQuery<Project> contentsQuery = jpaQueryFactory.selectFrom(project);
        JPAQuery<Long> countQuery = jpaQueryFactory.select(project.count()).from(project);

        JPAQuery<Project> whereContentsQuery = customProjectJPAQuery.addWhere(contentsQuery, searchDto, pageable);
        JPAQuery<Long> whereCountQuery = customLongJPAQuery.addWhere(countQuery, searchDto, pageable);

        return PageableExecutionUtils.getPage(whereContentsQuery.fetch(), pageable, () -> whereCountQuery.fetchOne());
    }

}

class CustomJPAQuery<T> extends JPAQuery<T> {

    public JPAQuery<T> addWhere(JPAQuery<T> query, SearchDto.Detail searchDto, Pageable pageable) {
        QProjectTag projectTag = QProjectTag.projectTag;
        QProject project = QProject.project;
        QProjectMember projectMember = QProjectMember.projectMember;

        // tags가 주어졌을 경우
        // projectTag에서 검색
        List<Long> tagIds = searchDto.getTags();
        if (!tagIds.isEmpty()) {
            query.where(project.id.in(
                    JPAExpressions.select(projectTag.project.id)
                            .from(projectTag)
                            .where(projectTag.tag.id.in(tagIds))
                            .groupBy(projectTag.project.id)
                            .having(projectTag.project.id.count().goe(tagIds.size()))));
        }

        // members가 주어졌을 경우
        // writedMember에서 검색 또는
        // projectMember에서 검색
        List<Long> memberIds = searchDto.getMembers();
        if (!memberIds.isEmpty()) {
            query.where(
                    project.writedMember.id.in(memberIds)
                            .or(project.id.in(
                                    JPAExpressions.select(projectMember.member.id)
                                            .from(projectMember)
                                            .where(projectMember.member.id.in(memberIds))
                                            .groupBy(projectMember.member.id)
                                            .having(projectMember.member.id.count().goe(memberIds.size())))));
        }

        // keyword가 주어졌을 경우
        String keyword = searchDto.getKeyword();
        if (!keyword.isBlank()) {
            query.where(project.name.contains(keyword));
        }

        // 페이지가 주어졌을 경우
        if (pageable != null) {
            query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());

            // 정렬이 주어졌을 경우
            if (!pageable.getSort().isEmpty()) {
                for (Sort.Order order : pageable.getSort()) {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    switch (order.getProperty()) {
                        case "likes":
                            query.orderBy(new OrderSpecifier<>(direction, project.likes));
                            break;
                        case "date":
                            query.orderBy(new OrderSpecifier<>(direction, project.createdDate));
                            break;
                        default:
                            throw new RuntimeException("sort 파라미터 잘못됨");
                    }
                }
            }
        }

        return query;
    }
}