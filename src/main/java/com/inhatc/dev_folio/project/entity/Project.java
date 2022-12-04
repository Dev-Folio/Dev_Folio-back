package com.inhatc.dev_folio.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.inhatc.dev_folio.category.entity.Comment;
import com.inhatc.dev_folio.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    private String thumbnail;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String detail;
    private String contents;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private int views = 0;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private int likes = 0;

    @OneToMany(mappedBy = "project")
    private List<ProjectTag> projectTags;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> contributedMembers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrote_member_id", nullable = false)
    private Member wroteMember;

    @OneToMany(mappedBy = "project")
    private List<Comment> comments;

    @OneToMany(mappedBy = "project")
    private List<GithubUrl> githubUrls;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;
}
