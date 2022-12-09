package com.inhatc.dev_folio.config.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.inhatc.dev_folio.constant.ErrorMessage;
import com.inhatc.dev_folio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * 인가 = 권한이 있는지 확인하는 것
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // /login에서 들어온 요청이라면 패쓰
        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 헤더 값 받아옴
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        // 헤더 값 검증
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // token 받아옴
        String token = authorizationHeader.substring("Bearer ".length());

        try{
            // JWT verify
            DecodedJWT decodedJWT = jwtTokenUtil.verify(token);

            // Subject(email) 받아옴
            String email = decodedJWT.getSubject();

            log.info("요청한 사용자 이메일 : {}", email);

            UserDetails userDetails = memberService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e){
            log.error(ErrorMessage.NOT_LOGIN.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessage.NOT_LOGIN.getMessage());
        }
    }
}
