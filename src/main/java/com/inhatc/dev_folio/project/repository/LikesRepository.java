package com.inhatc.dev_folio.project.repository;

import com.inhatc.dev_folio.project.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    boolean existsByProjectIdAndMemberEmail(Long projectId, String memberEmail);

    Optional<Likes> findByProjectIdAndMemberEmail(Long projectId, String memberEmail);
}
