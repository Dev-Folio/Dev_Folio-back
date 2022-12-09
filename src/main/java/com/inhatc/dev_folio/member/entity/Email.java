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
    private Long id;

    @Column
    private String email;

    @Column
    private String authToken;

    @Column
    private boolean expired;

    @Column
    private LocalDateTime expireDate;

    @Builder
    public Email(String email, String authToken, Boolean expired){
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
//        plusMinutes = 설정한 시간만큼 더합니다. 생성한 수 5분까지만 유효하도록 설정한다.
        this.expireDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
    }

//    expired가 true면 token을 사용할 수 있게 한다.
    public void useToken(){
        this.expired = true;
    }

}
