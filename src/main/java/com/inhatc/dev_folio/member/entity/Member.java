package com.inhatc.dev_folio.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.inhatc.dev_folio.member.constant.Role;
import com.inhatc.dev_folio.project.entity.Project;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    private String phone;
    private String info;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributed_project_id")
    private Project contributedProject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writed_project_id")
    private Project writedProject;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private ChangePassword passwordChange;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registeredDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;
}
