package com.inhatc.dev_folio.member.controller;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.dto.MemberRegDto;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 검색
     */
    @GetMapping("/search/member")
    public List<MemberDto.View> searchMember(@RequestParam String query) {
        log.info("searchMember(query:{})", query);
        return memberService.searchMember(query);
    }

    //    로그인
    @GetMapping("/login")
    public String login() {
        log.info("로그인 접속");
        return "";
    }


    @GetMapping("/signup")
    public void signup(){
    }

    @PostMapping(value = "/signup")
    public Member memberSave(Member member){

        return memberService.saveMember(member);
    }

//    @PostMapping("/signup/check")
//    public boolean signupCheck(@RequestBody ){
//        return true;
//    }


    /**
     * 프로필 조회
     */
    @GetMapping("/profile/{memberId}")
    public MemberDto.GetProfile getProfile(@PathVariable Long memberId) {
        log.info("getProfile(memberId:{})", memberId);
        return memberService.getMemberProfile(memberId);
    }

    /**
     * 프로필 수정
     */
    @PostMapping("/profile/{memberId}")
    public void postProfile(@PathVariable Long memberId, @RequestBody MemberDto.SetProfile setProfile) {
        log.info("postProfile(memberId:{}, setProfile:{}", memberId, setProfile);
        memberService.updateProfile(memberId, setProfile);
    }

    /**
     * 로그인 확인 (시큐리티 authenticated)
     */
    @GetMapping("/member")
    public MemberDto.View checkLogin(Principal principal) {
        log.info("checkLogin()");
        return memberService.getMemberView(principal.getName());
    }
}
