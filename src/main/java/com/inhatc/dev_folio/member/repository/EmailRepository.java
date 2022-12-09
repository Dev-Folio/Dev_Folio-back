package com.inhatc.dev_folio.member.repository;

import com.inhatc.dev_folio.member.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long>, EmailCustomRepository {
}
