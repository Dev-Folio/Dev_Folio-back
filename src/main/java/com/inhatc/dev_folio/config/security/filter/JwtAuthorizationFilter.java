package com.inhatc.dev_folio.config.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    /**
     * 필터를 지나면서 SecurityContextHolder에 사용자 정보를 담게 된다.
     * 만약 담지 않는다면, 그대로 로그인 되지 않은 사용자가 되어버린다.
     * filterChain.doFilter() 메소드는 필터를 그냥 패스하게 만든다.
     */
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
            log.warn("토큰 정보 없음");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // token 받아옴
            String token = authorizationHeader.substring("Bearer ".length());

            // JWT verify => JWT가 유효하지 않을 시 예외 발생 가능
            DecodedJWT decodedJWT = jwtTokenUtil.verify(token);

            // Subject(email) 받아옴
            String email = decodedJWT.getSubject();

            log.info("요청한 사용자 이메일 : {}", email);

            // emali로 userDetails 정보 가져옴 => 해당 email의 유저가 없을 시 예외 발생 가능
            UserDetails userDetails = memberService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JWTVerificationException e) {
            log.warn("JWT 파싱 중 예외 발생");
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
