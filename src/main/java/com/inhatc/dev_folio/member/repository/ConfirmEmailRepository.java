package com.inhatc.dev_folio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhatc.dev_folio.member.entity.ConfirmEmail;

public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Long> {

}
