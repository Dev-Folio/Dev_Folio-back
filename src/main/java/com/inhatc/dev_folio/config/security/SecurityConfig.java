package com.inhatc.dev_folio.config.security;

import com.inhatc.dev_folio.member.handler.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        이걸 계속 들고 다녀야 좋다 하긴 했는데 나중에 말해보지요
        http.cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureHandler(loginFailHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");


        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                // .mvcMatchers("/").permitAll()
                .anyRequest().permitAll();

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

}
