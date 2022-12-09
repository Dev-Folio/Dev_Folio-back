package com.inhatc.dev_folio.member.repository;

import com.inhatc.dev_folio.member.entity.Email;

import java.time.LocalDateTime;
import java.util.Optional;

//얘를 추상 클래스로 만들 거니까 JPARepository를 extends 하지 않는다.
//왜냐면 얘를 extends하면 메소드를 다... overriding 해야 함
public interface EmailCustomRepository {
    Optional<Email> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime);
}
