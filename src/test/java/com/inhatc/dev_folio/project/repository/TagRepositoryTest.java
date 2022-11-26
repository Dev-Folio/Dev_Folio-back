package com.inhatc.dev_folio.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inhatc.dev_folio.project.entity.Tag;

@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @DisplayName("기본 Tag 데이터 생성")
    @Test
    void saveTags() {
        Tag javascript = Tag.builder()
                .name("JavaScript")
                .color("#F7DF1E")
                .build();
        tagRepository.save(javascript);

        Tag java = Tag.builder()
                .name("Java")
                .color("#007396")
                .build();
        tagRepository.save(java);

        Tag android = Tag.builder()
                .name("Android")
                .color("#3DDC84")
                .build();
        tagRepository.save(android);

        Tag angular = Tag.builder()
                .name("Angular")
                .color("#DD0031")
                .build();
        tagRepository.save(angular);

        Tag ios = Tag.builder()
                .name("iOS")
                .color("#000000")
                .build();
        tagRepository.save(ios);
    }

}
