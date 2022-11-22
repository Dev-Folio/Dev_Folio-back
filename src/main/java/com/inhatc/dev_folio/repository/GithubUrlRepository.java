package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.GithubUrl;

public interface GithubUrlRepository extends JpaRepository<GithubUrl, Long> {

}
