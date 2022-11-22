package com.inhatc.dev_folio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class ConfirmEmail {
    @Id
    @GeneratedValue
    @Column(name = "confirm_email_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String token;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime expiredDate;

}
