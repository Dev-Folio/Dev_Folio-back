package com.inhatc.dev_folio.project.repository;

import com.inhatc.dev_folio.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
