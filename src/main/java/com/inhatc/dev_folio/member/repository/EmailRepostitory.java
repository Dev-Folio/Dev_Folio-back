package com.inhatc.dev_folio.member.repository;

import com.inhatc.dev_folio.member.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepostitory extends JpaRepository<Email, Long>, EmailCustomRepository {
}
