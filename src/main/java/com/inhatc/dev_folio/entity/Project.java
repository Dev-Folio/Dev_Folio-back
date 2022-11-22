package com.inhatc.dev_folio.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
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
    private int views;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likes;

    @OneToMany(mappedBy = "project")
    private List<ProjectTag> projectTags;

    @OneToMany(mappedBy = "contributedProject")
    private List<Member> contributedMembers;

    @OneToOne(mappedBy = "writedProject")
    @JoinColumn(nullable = false)
    private Member writedMember;

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
