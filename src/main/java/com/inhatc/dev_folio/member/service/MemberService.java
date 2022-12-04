package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.mapper.MemberMapper;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    public List<MemberDto.View> searchMember(MemberDto.Search search) {
        String query = search.getQuery();
        List<Member> members = memberRepository.findByEmailContainsOrNameContains(query, query);
        return MemberMapper.INSTANCE.memberListToViewList(members);
    }
}
