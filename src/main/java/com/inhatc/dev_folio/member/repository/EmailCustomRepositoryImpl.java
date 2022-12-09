package com.inhatc.dev_folio.member.repository;

import com.inhatc.dev_folio.member.entity.Email;
import com.inhatc.dev_folio.member.entity.QEmail;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

public class EmailCustomRepositoryImpl implements EmailCustomRepository {

    JPAQueryFactory jpaQueryFactory;

    public EmailCustomRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Optional<Email> findValidAuthByEmail(String email, String authToken, LocalDateTime currentTime) {
        Email emailAuth = jpaQueryFactory
                .selectFrom(QEmail.email1)
                .where(QEmail.email1.email.eq(email), //이메일 주소 일치하는지 확인
                        QEmail.email1.authToken.eq(authToken), //token 맞는지 확인
                        QEmail.email1.expireDate.goe(currentTime), //크거나 같은 걸 쓸 때 말함. 그러니까 exprieDate가 currenTime보다 커야 함. 
                        QEmail.email1.expired.eq(false))//expired가 false인지 확인
                .fetchFirst(); //조회 대상이 1건이던 1건 이상이던 항상 1건만 반환한다.

        return Optional.ofNullable(emailAuth);
    }
}
