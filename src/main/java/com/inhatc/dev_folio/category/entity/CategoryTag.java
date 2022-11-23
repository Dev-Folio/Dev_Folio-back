package com.inhatc.dev_folio.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inhatc.dev_folio.project.entity.Tag;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class CategoryTag {
    @Id
    @GeneratedValue
    @Column(name = "category_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
