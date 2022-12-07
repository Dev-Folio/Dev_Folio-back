package com.inhatc.dev_folio.member.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException{
        logger.info("login fail handler");

        String errorMessage;
        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException)
        {
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요";
        }
        else if (e instanceof UsernameNotFoundException)
        {
            errorMessage = "존재하지 않는 아이디 입니다. 회원가입을 진행해주세요";
        }
        else
        {
            errorMessage = "유저 확인이 되지 않습니다. \n 관리팀으로 메일 보내주시면 빠른 확인 도와드리겠습니다. \n pinokioandmj@gmail.com";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");// 한글 인코딩 깨지는 문제 방지
        setDefaultFailureUrl("/login/error?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
    }
}
