package com.inhatc.dev_folio.config.security;

import com.inhatc.dev_folio.constant.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
인증 실패 시 처리하는 클래스로
인증되지 않은 사용자의 리소스에 대한 접근 처리를 담당한다
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 추상메소드 override
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error(ErrorMessage.NOT_LOGIN.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessage.NOT_LOGIN.getMessage());
    }
}
