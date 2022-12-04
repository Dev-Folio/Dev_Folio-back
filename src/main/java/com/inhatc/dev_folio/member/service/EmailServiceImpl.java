package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{
//    추상메소드 오버라이드

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

/*
메일을 보낼 때 쓰는 JAVA API.
MimeMessage = 멀티파트 데이터를 보낼 때 사용한다.
SimpleMailMessage = 메일 제목, 단순 텍스트 내용을 전달받아 보낼 때 사용한다.
 */
    private MimeMessage createMessage(String to) throws Exception {
        log.info("메일을 보내는 대상: " + to);
        log.info("보낸 인증 번호 : " + ePw);

        MimeMessage message = emailSender.createMimeMessage();
//        받는 사람이 없거나 확인이 안 될 경우를 대비하여 예외를 던져준다
        message.addRecipients(Message.RecipientType.TO, to); //메일 보낼 사람
        message.setSubject("인증 이메일을 보내드립니다."); //제목

//        보낼 메세지
        String mailMsg ="";
        mailMsg+= "<div style='margin:20px;'>";
        mailMsg+= "<h1> Dev-Folio Team입니다. </h1>";
        mailMsg+= "<br>";
        mailMsg+= "<p>저희 Dev-Folio에 가입해주신 여러분들을 진심으로 환영합니다.<p>";
        mailMsg+= "<p>아래의 코드를 복사 후 입력해주세요.<p>";
        mailMsg+= "<br>";
        mailMsg+= "<p>앞으로 저희와 함께 프로젝트를 해쳐나가보아요.<p>";
        mailMsg+= "<br>";
        mailMsg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        mailMsg+= "<h3 style='color:blue;'>회원가입 인증 코드</h3>";
        mailMsg+= "<div style='font-size:130%'>";
        mailMsg+= "CODE : <strong>";
        mailMsg+= ePw+"</strong><div><br/> ";
        mailMsg+= "</div>";
        message.setText(mailMsg, "utf-8", "html"); // 위에 입력한 내용 set
        message.setFrom(new InternetAddress("pinokioandmj@gmail.com","Dev-Folio Team"));//보내는 사람

        return message;
    }
    private static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

//        인증코드는 8자리로 설정
        for(int i = 0; i<8; i++){
//            숫자
            int index = random.nextInt(3); //0~2까지 랜덤

            switch (index){
                case 0:
//                    a~z 즉, 소문자 영어
                    key.append((char) ((int) (random.nextInt(26))+97));
                    break;
                case 1:
//                    A~Z 즉, 대문자 영어.
                    key.append((char) ((int) (random.nextInt(26))+65));
                    break;
                case 2:
//                    0~9까지의 숫자
                    key.append((random.nextInt(10)));
                    break;
            }
        }
//        key를 문자열로 반환
        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);

        try{
            emailSender.send(message);
        }catch (MailException me){
            me.printStackTrace();
            throw new IllegalStateException("메일 발송에 실패했습니다.");
        }
//        인증 이메일 내용 리턴
        return ePw;
    }
}
