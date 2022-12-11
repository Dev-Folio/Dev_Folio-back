package com.inhatc.dev_folio.config.security;

import com.inhatc.dev_folio.config.security.filter.JwtAuthenticationFilter;
import com.inhatc.dev_folio.config.security.filter.JwtAuthorizationFilter;
import com.inhatc.dev_folio.config.security.filter.JwtTokenUtil;
import com.inhatc.dev_folio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //        이걸 계속 들고 다녀야 좋다 하긴 했는데 나중에 말해보지요
        http.cors().and().csrf().disable();

        // session 인증을 사용하지 않기 때문에 Stateless로 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // UsernamePasswordAuthenticationFilter 대신 JwtAuthenticationFilter를 집어넣는다.
        http.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenUtil));
        // UsernamePasswordAuthenticationFilter 전에 JwtAuthorizationFilter룰 집어넣는다.
        http.addFilterBefore(new JwtAuthorizationFilter(memberService, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**").permitAll()

                // 좋아요 여부 표시할 때는 인증 필요
                .mvcMatchers("/project/*/like").authenticated()

                // 댓글 작성, 수정, 삭제에는 인증 필요
                .mvcMatchers(HttpMethod.GET, "/project/*/comment").permitAll()
                .mvcMatchers(HttpMethod.POST, "/project/*/comment").authenticated()
                .mvcMatchers(HttpMethod.PUT, "/project/comment/*").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/project/comment/*").authenticated()

                // 프로젝트 생성, 수정, 삭제에는 인증 필요
                .mvcMatchers(HttpMethod.GET, "/project/*").permitAll()
                .mvcMatchers(HttpMethod.POST, "/project").authenticated()
                .mvcMatchers(HttpMethod.PUT, "/project/*").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/project/*").authenticated()

                // 로그인 확인
                .mvcMatchers(HttpMethod.GET, "/member").authenticated()

                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/").hasRole("ADMIN")
                .anyRequest().permitAll();

        // 예외 처리
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint) // 401
                .accessDeniedHandler(customAccessDeniedHandler); // 403

        return http.build();
    }
}
