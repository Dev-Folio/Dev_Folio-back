package com.inhatc.dev_folio.member.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.inhatc.dev_folio.member.dto.MemberDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.inhatc.dev_folio.member.constant.Role;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectMember;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
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

    @OneToMany(mappedBy = "member")
    private List<ProjectMember> contributedProjects;

    @OneToMany(mappedBy = "wroteMember")
    private List<Project> wroteProjects;

    @OneToOne(mappedBy = "member")
    private ChangePassword passwordChange;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registeredDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;


    public void updateInfo(String info){
        this.info = info;
    }
}
