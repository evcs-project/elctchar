package com.elct.elctchar.web.member.controller;

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
    public Long createMember(@RequestBody MemberCreateRequestDto requestDto)
    {
        Member member = memberService.createMember(requestDto.getUsername(), requestDto.getPassword());
        return member.getMemberId();
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable(value = "memberId") Long memberId)
    {
        memberService.deleteMember(memberId);
    }

    // TODO : 중복회원조회
    @GetMapping("/duplicate-check")
    public void checkDuplicateMember(@RequestParam(value="nickName") String nickName)
    {
        memberService.checkDuplicateMember(nickName);
    }

    // TODO : 비밀변호 변경
    @PutMapping("/change-password")
    public void changePassword(@RequestParam(value="memberId") Long memberId,
                               @RequestParam(value="currentPassword") String currentPassword,
                               @RequestParam(value="newPassword") String newPassword)
    {
        memberService.changePassword(memberId, newPassword);
    }

    @PostMapping("/station")
    public void addStation(
            @RequestParam(value = "memberId") Long memberId,
            @RequestParam(value = "csId") String csId)
    {
        memberService.addStation(memberId, csId);
    }
}
