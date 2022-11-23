package com.inhatc.dev_folio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inhatc.dev_folio.dto.ProjectDto;

@Service
public class ProjectService {

    public List<ProjectDto> getSearchData() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        return projectDtos;
    }

}
