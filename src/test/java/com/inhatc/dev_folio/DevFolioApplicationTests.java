package com.inhatc.dev_folio;

import com.inhatc.dev_folio.member.constant.Role;
import com.inhatc.dev_folio.member.entity.Member;
import com.inhatc.dev_folio.member.repository.MemberRepository;
import com.inhatc.dev_folio.project.entity.Project;
import com.inhatc.dev_folio.project.entity.ProjectTag;
import com.inhatc.dev_folio.project.entity.Tag;
import com.inhatc.dev_folio.project.repository.ProjectRepository;
import com.inhatc.dev_folio.project.repository.ProjectTagRepository;
import com.inhatc.dev_folio.project.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class DevFolioApplicationTests {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProjectTagRepository projectTagRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("테스트 데이터 삽입")
    @Test
    void saveTestData() {
        // Save Members
        Member admin = Member.builder()
                .email("201845092@itc.ac.kr")
                .info("테스트 관리자입니다.")
                .phone("010-0000-0000")
                .name("관리자")
                .password(passwordEncoder.encode("1234"))
                .role(Role.ADMIN)
                .build();
        memberRepository.save(admin);
        Member member = Member.builder()
                .email("201845093@itc.ac.kr")
                .info("테스트 멤버입니다.")
                .phone("010-0000-0000")
                .name("멤버")
                .password(passwordEncoder.encode("1234"))
                .role(Role.MEMBER)
                .build();
        memberRepository.save(member);

        // Save Projects
        for (int i = 0; i < 10; i++) {
            Project project = Project.builder()
                    .thumbnail("testAwsURL")
                    .name("테스트 프로젝트" + i)
                    .startDate(LocalDate.now().plusDays(i))
                    .endDate(LocalDate.now().plusDays(i + 1))
                    .detail("테스트 프로젝트의 detail")
                    .contents("테스트 프로젝트의 contents")
                    .wroteMember(member)
                    .build();
            projectRepository.save(project);
        }

        // Save Tags
        Tag javascript = Tag.builder()
                .name("JavaScript")
                .color("#F7DF1E")
                .build();
        tagRepository.save(javascript);

        Tag java = Tag.builder()
                .name("Java")
                .color("#007396")
                .build();
        tagRepository.save(java);

        Tag android = Tag.builder()
                .name("Android")
                .color("#3DDC84")
                .build();
        tagRepository.save(android);

        Tag angular = Tag.builder()
                .name("Angular")
                .color("#DD0031")
                .build();
        tagRepository.save(angular);

        Tag ios = Tag.builder()
                .name("iOS")
                .color("#000000")
                .build();
        tagRepository.save(ios);

        // Save ProjectTags
        List<Project> projects = projectRepository.findAll();

        for (Project project : projects) {
            ProjectTag projectTag = ProjectTag.builder().project(project).tag(javascript).build();
            projectTagRepository.save(projectTag);
        }
    }
}
