package com.inhatc.dev_folio.member.dto;

import com.inhatc.dev_folio.member.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
public class MemberRegDto {

    private Long id;
    private String email;
    private String token;

    @Builder
    public MemberRegDto(Long id, String email, String token){
        this.id = id;
        this.email = email;
        this.token = token;
    }
}
