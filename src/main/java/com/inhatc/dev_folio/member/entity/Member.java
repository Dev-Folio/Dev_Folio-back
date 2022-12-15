package com.inhatc.dev_folio.member.entity;

import com.inhatc.dev_folio.member.constant.Role;
import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.project.entity.Likes;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectMember;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<ProjectMember> contributedProjects = new ArrayList<>();

    @OneToMany(mappedBy = "wroteMember")
    private List<Project> wroteProjects = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "change_password_id")
    private ChangePassword changePassword;

    @OneToMany(mappedBy = "member")
    private List<Likes> likes = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registeredDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    public static Member createUser(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.MEMBER);
        return member;
    }

    public void updateInfo(String info) {
        this.info = info;
    }
}
