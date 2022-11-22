package com.inhatc.dev_folio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class ProfileImage {
    @Id
    @GeneratedValue
    @Column(name = "profile_image_id")
    private Long id;

    @Column(nullable = false)
    private String url;

    @OneToOne(mappedBy = "profileImage")
    private Member member;
}
