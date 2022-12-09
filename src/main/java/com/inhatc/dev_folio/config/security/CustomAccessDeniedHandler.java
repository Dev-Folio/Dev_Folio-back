package com.inhatc.dev_folio.config.security;

import com.inhatc.dev_folio.constant.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error(ErrorMessage.FORBIDDEN.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, ErrorMessage.FORBIDDEN.getMessage());
    }
}
