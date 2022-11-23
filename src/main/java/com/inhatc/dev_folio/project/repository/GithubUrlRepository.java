package com.inhatc.dev_folio.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.project.entity.GithubUrl;

public interface GithubUrlRepository extends JpaRepository<GithubUrl, Long> {

}
