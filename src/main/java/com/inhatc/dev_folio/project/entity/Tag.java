package com.inhatc.dev_folio.project.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.inhatc.dev_folio.category.entity.CategoryTag;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String color;

    @OneToMany(mappedBy = "tag")
    private List<ProjectTag> projectTags;

    @OneToMany(mappedBy = "tag")
    private List<CategoryTag> categoryTags;

}
