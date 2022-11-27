package com.inhatc.dev_folio.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.project.dto.TagDto;
import com.inhatc.dev_folio.project.entity.Tag;
import com.inhatc.dev_folio.project.mapper.TagMapper;
import com.inhatc.dev_folio.project.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return TagMapper.INSTANCE.tagListToTagDtoList(tags);
    }

}
