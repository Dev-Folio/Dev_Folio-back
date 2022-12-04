package com.inhatc.dev_folio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.member.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmailContainsOrNameContains(String email, String Name);
}
