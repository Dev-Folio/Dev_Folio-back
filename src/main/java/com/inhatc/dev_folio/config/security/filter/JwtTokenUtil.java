package com.inhatc.dev_folio.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt-secret}")
    private String jwtSecret;

    // 토큰 만료 시간 6시간
    private final long TOKEN_EXPIRED_TIME = 6 * 60 * 60 * 1000L;

    public DecodedJWT verify(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String create(User user, HttpServletRequest request) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(new Date().getTime() + TOKEN_EXPIRED_TIME))
                .withClaim("email", user.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
    }

}
