package com.inhatc.dev_folio.project.entity;

import java.util.List;

import javax.persistence.*;

import com.inhatc.dev_folio.category.entity.CategoryTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
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
