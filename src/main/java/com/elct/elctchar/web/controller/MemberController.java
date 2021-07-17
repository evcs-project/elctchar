package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberCreateRequestApiDto;
import com.elct.elctchar.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public Long createMember(MemberCreateRequestApiDto requestDto)
    {
        Member member = memberService.create(requestDto.getUsername(), requestDto.getPassword());
        return member.getMemberId();
    }

    // TODO : 멤버 삭제 추가 필요
//    public void deleteMember(Long memberId)
//    {
//
//    }
}
