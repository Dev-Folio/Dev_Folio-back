package com.inhatc.dev_folio.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_image")
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    private Long id;

    @Column(nullable = false)
    private String url;

    @OneToOne(mappedBy = "profileImage")
    private Member member;

    public void updateUrl(String url){
        this.url = url;
    }
}
