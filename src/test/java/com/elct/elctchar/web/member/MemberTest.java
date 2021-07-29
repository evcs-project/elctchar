package com.elct.elctchar.web.member;

import com.elct.elctchar.web.auth.AuthUtil;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Assertions;

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
        memberService.changePassword(member.getMemberId(), newPassword);
        Member findMember = memberRepository.findById(member.getMemberId()).get();
        Assertions.assertTrue(passwordEncoder.matches(newPassword, findMember.getPassword()));
    }

    @Test
    @DisplayName("동일한 비밀번호로 변경 시 오류 테스트")
    void equalPasswordErrorTest()
    {
        Member member = memberService.createMember("user1", "pwd1");
        Assertions.assertThrows(GlobalApiException.class, () -> {
            memberService.changePassword(member.getMemberId(), "pwd1");
        });
    }

    @Test
    @DisplayName("중복된 회원 가입 테스트")
    void createMemberTest()
    {
        memberService.createMember("user3", "password");

        Assertions.assertThrows(GlobalApiException.class, ()-> {
            memberService.createMember("user3", "password");
        });
    }

    @Test
    @DisplayName("멤버 ID 로 삭제 테스트")
    void deleteMemberTest() {
        Member member = memberService.createMember("user1", "pwd1");
        memberRepository.delete(member);
    }

    @Test
    @DisplayName("중복된 회원 체크 테스트")
    void equalNicknameErrorTest()
    {
        memberService.createMember("user3", "pwd1");

        Assertions.assertThrows(GlobalApiException.class, ()-> {
            memberService.checkDuplicateMember("user3");}
        );
    }
}
