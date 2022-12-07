package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.constant.ErrorMessage;
import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.entity.ProfileImage;
import com.inhatc.dev_folio.member.mapper.MemberMapper;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import com.inhatc.dev_folio.member.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ProfileImageRepository profileImageRepository;

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
            throw  new IllegalStateException(ErrorMessage.MEMBER_ALREADY_REGISTERED.getMessage());
        }
    }

    public MemberDto.GetProfile getMemberProfile(Long memberId) {
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage()));
        return MemberMapper.INSTANCE.memberToProfilePage(foundMember);
    }

    public void updateProfile(Long memberId, MemberDto.SetProfile setProfile) {
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage()));
        ProfileImage profileImage = foundMember.getProfileImage();

        // 프로필 이미지가 없을 경우엔 새로 만듦, 있을 경우 url 업데이트
        if (profileImage == null){
            profileImage = ProfileImage.builder()
                    .member(foundMember)
                    .url(setProfile.getImage())
                    .build();
        } else {
            profileImage.updateUrl(setProfile.getImage());
        }
        profileImageRepository.save(profileImage);

        foundMember.updateInfo(setProfile.getInfo());
        memberRepository.save(foundMember);
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
