package com.inhatc.dev_folio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
