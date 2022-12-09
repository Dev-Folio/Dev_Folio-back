package com.inhatc.dev_folio.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync //비동기 메소드 사용 가능하게 함
@RequiredArgsConstructor
public class EmailService {

   private final JavaMailSender javaMailSender;

//   전송하는 동안 블럭 상태에 놓이게 되니까 그거 방지 위해 비동기
   @Async
    public void sendMail(String email, String auhToken){
       SimpleMailMessage sm = new SimpleMailMessage();
//       보낼 사용자 지정
        sm.setTo(email + "@itc.ac.kr");
//       메일 제목 지정
        sm.setSubject("Dev-Folio Team입니다. 이메일 인증입니다.");
//        메일 내용 지정
        sm.setText("http://localhost:8080/signup?token=" + auhToken);

//        메일 보내기
        javaMailSender.send(sm);

   }
}
