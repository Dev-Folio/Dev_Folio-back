package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        duplicatedMember(member);
//       이메일 보내기 확인
        return memberRepository.save(member);
    }

    private void duplicatedMember(Member member) {
//         이미 가입된 회원이 있는지 member 테이블에서 가져온다
        Member findMember = memberRepository.findByEmail(member.getEmail());
//        값이 비어있지 않으면
        if( findMember != null){
//            이미 가입된 회원이라는 throw 던지기 -> 이거 나중에 model에 담아서
//            프론트에 전달할 예정 ^0^
            throw  new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
