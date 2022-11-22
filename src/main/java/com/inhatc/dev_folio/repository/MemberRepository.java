package com.inhatc.dev_folio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
