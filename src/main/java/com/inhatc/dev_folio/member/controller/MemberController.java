package com.inhatc.dev_folio.member.controller;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/search/member")
    public List<MemberDto.View> searchMember(@RequestBody MemberDto.Search search) {
        log.info("searchMember(query:{})", search.getQuery());
        return memberService.searchMember(search);
    }

//    로그인
    @GetMapping("/login")
    public String login(){
    return "";
    }


    @GetMapping("/profile/{memberId}")
    public MemberDto.GetProfile getProfile(@PathVariable Long memberId) {
        log.info("getProfile(memberId:{})", memberId);
        return memberService.getMemberProfile(memberId);
    }

    @PostMapping("/profile/{memberId}")
    public void postProfile(@PathVariable Long memberId, @RequestBody MemberDto.SetProfile setProfile){
        log.info("postProfile(memberId:{}, setProfile:{}", memberId, setProfile);
        memberService.updateProfile(memberId, setProfile);
    }
}
