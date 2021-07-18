package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.dto.MemberCreateRequestDto;
import com.elct.elctchar.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public Long createMember(MemberCreateRequestDto requestDto)
    {
        Member member = memberService.createMember(requestDto.getUsername(), requestDto.getPassword());
        return member.getMemberId();
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable(value = "memberId") Long memberId)
    {
        memberService.deleteMember(memberId);
    }
}
