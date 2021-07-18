package com.elct.elctchar.web.member;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * 단위 테스트든 통합테스트를 먼저 같은 파일에 작성하고 나중에 분리
 */
@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호 변경 성공 테스트")
    void changePasswordTest()
    {
        Member member = memberService.createMember("user1", "pwd1");
        String newPassword = "pwd2";
        memberService.changePassword(member.getMemberId(), member.getPassword(), newPassword);
        Member findMember = memberRepository.findById(member.getMemberId()).get();
        Assertions.assertThat(passwordEncoder.matches(newPassword, findMember.getPassword())).isTrue();
    }

    @Test
    @DisplayName("비밀번호 변경시에 이전 비밀번호와 같으면 에러")
    void equalPasswordErrorTest()
    {
        Member member = memberService.createMember("user1", "pwd1");
        Assertions.assertThatThrownBy(() -> {
            memberService.changePassword(member.getMemberId(), member.getPassword(), "pwd1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("회원가입 등록")
    void createMemberTest()
    {
        Member member = memberService.createMember("username", "password");
        Member newMember = memberRepository.findById(member.getMemberId()).get();

        Assertions.assertThat(newMember.getMemberId()).isNotNull();
    }
}
