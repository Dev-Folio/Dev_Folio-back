package com.inhatc.dev_folio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findTop5ByEmailContainsOrNameContains(String email, String Name);

    Optional<Member> findByEmail(String email);
}
