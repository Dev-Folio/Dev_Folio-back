package com.inhatc.dev_folio.member.service;

import com.inhatc.dev_folio.constant.ErrorMessage;
import com.inhatc.dev_folio.member.dto.MemberDto;
import com.inhatc.dev_folio.member.dto.MemberRegDto;
import com.inhatc.dev_folio.member.entity.Email;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.entity.ProfileImage;
import com.inhatc.dev_folio.member.exception.EmailAlreadyException;
import com.inhatc.dev_folio.member.mapper.MemberMapper;
import com.inhatc.dev_folio.member.repository.EmailRepository;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import com.inhatc.dev_folio.member.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ProfileImageRepository profileImageRepository;
    private final EmailRepository emailRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public List<MemberDto.View> searchMember(MemberDto.Search search) {
        String query = search.getQuery();
        List<Member> members = memberRepository.findByEmailContainsOrNameContains(query, query);
        return MemberMapper.INSTANCE.memberListToViewList(members);
    }


    //    이메일 중복 있는지 확인 후 이메일 보내기
    private MemberRegDto regMember(MemberRegDto regDto) {
        //        일단 중복 이메일 있는지 확인
        duplicatedMember(regDto.getEmail());
        Email email = emailRepository.save(
                Email.builder()
                        .email(regDto.getEmail())
                        .authToken(UUID.randomUUID().toString())
                        .expired(false)
                        .build());

        emailService.sendMail(email.getEmail(), email.getAuthToken());

        return MemberRegDto.builder()
                .id(email.getId())
                .email(email.getEmail())
                .token(email.getAuthToken())
                .build();
    }

    //    이메일 인증 실행
    @Transactional
    public void confirmEmail(MemberRegDto regDto) {
//        이메일 인증 발급 됐는지 확인
        Email email = emailRepository.findValidAuthByEmail(regDto.getEmail(), regDto.getToken(), LocalDateTime.now())
                .orElseThrow(EntityNotFoundException::new);

        //        한 번 사용했으니까 사용 다시 못 하게 막아놓는다.
        email.useToken();

    }

    //    회원가입 계속 진행
    public Member saveMember(Member member) {

        //        로그 찍어보기 위함.
        Member myMember = memberRepository.save(member);
        log.info("제대로 값이 들어갔나요 " + myMember);

        return memberRepository.save(member);
    }

    private void duplicatedMember(String email) {
        //        같은 값이 존재하면 안되니까 이메일이 존재할 경우 exception을 던진다
        if (memberRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyException();
    }

    public MemberDto.GetProfile getMemberProfile(Long memberId) {
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));
        return MemberMapper.INSTANCE.memberToProfilePage(foundMember);
    }

    public void updateProfile(Long memberId, MemberDto.SetProfile setProfile) {
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_ID_NOT_FOUND.getMessage()));
        ProfileImage profileImage = foundMember.getProfileImage();

        // 프로필 이미지가 없을 경우엔 새로 만듦, 있을 경우 url 업데이트
        if (profileImage == null) {
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("=========>" + email + "제대로 읽고 있나요");
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_EMAIL_NOT_FOUND.getMessage()));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
