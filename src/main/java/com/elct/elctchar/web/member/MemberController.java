package com.elct.elctchar.web.member;

import com.elct.elctchar.web.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable(value = "memberId") Long memberId)
    {
        memberService.deleteMember(memberId);
    }
// 리뷰 사용자 아이디 조회
    // 리뷰 사용자아이디로 생성
}
