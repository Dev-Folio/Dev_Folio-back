package com.inhatc.dev_folio.project.mapper;

import com.inhatc.dev_folio.project.entity.GithubUrl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GithubUrlMapper {
    GithubUrlMapper INSTANCE = Mappers.getMapper(GithubUrlMapper.class);

    static String getGithubUrl(GithubUrl githubUrl) {
        return githubUrl.getUrl();
    }

    List<String> githubUrlListToStringList(List<GithubUrl> githubUrls);

}
