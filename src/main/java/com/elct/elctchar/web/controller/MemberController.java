package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.dto.MemberCreateRequestDto;
import com.elct.elctchar.web.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/member")
@Api(tags = "멤버 관련 Api")
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

    @GetMapping("/duplicate-check")
    public void checkDuplicateMember(@RequestParam(value="nickName") String nickName)
    {
        memberService.checkDuplicateMember(nickName);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestParam(value="memberId") Long memberId,
                               @RequestParam(value="currentPassword") String currentPassword,
                               @RequestParam(value="newPassword") String newPassword)
    {
        memberService.changePassword(memberId, newPassword);
    }

    @PostMapping("/station/{csId}")
    public void addStation(
            @PathVariable(value = "csId") String csId)
    {
        memberService.addStation(csId);
    }
}
