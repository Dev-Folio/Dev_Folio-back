package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.mapper.MemberMapper;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public List<MemberDto.View> searchMember(MemberDto.Search search) {
        String query = search.getQuery();
        List<Member> members = memberRepository.findByEmailContainsOrNameContains(query, query);
        return MemberMapper.INSTANCE.memberListToViewList(members);
    }

    public Member saveMember(Member member){
//        중복된 멤버 확인
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

//    로그인
    @Override
    public UserDetails loadUserByUsername(String email){
        log.info("=========>" + email + "제대로 읽고 있나요");
                Member member = memberRepository.findByEmail(email);

                return User.builder()
                        .username(member.getEmail())
                        .password(member.getPassword())
                        .roles(member.getRole().toString())
                        .build();
    }

}
