package com.inhatc.dev_folio.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {
//    5분 후 토큰 만료
    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
//    이거 이렇게 해도 되는지 물어봐야겠당
    private static Member member;
    
    public static Email createEmailToken(Long memberId){
        Email email = new Email();
        email.expirationDate = LocalDateTime.now()
                .plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
        email.expired = false;
        memberId = member.getId();

        return email;
    }

//    토큰 만료시
    public void setTokenExpired(){
        this.expired = true;
    }

}
