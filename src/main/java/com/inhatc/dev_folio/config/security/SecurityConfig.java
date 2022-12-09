package com.inhatc.dev_folio.config.security;

import com.inhatc.dev_folio.config.security.filter.JwtAuthenticationFilter;
import com.inhatc.dev_folio.config.security.filter.JwtAuthorizationFilter;
import com.inhatc.dev_folio.config.security.filter.JwtTokenUtil;
import com.inhatc.dev_folio.member.handler.LoginFailHandler;
import com.inhatc.dev_folio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        // http.formLogin()
        //         .loginPage("/login")
        //         .defaultSuccessUrl("/")
        //         .usernameParameter("email")
        //         .passwordParameter("password")
        //         .failureHandler(loginFailHandler())
        //         .and()
        //         .logout()
        //         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        //         .logoutSuccessUrl("/");

        // UsernamePasswordAuthenticationFilter 전에 JwtAuthorizationFilter룰 집어넣는다.
        http.addFilterBefore(new JwtAuthorizationFilter(memberService, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter 대신 JwtAuthenticationFilter를 집어넣는다.
        http.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenUtil));

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler loginFailHandler() {
        return new LoginFailHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
