package com.elct.elctchar.web.member.controller;

import com.elct.elctchar.web.auth.AuthUtil;
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
        memberService.changePassword(memberId, currentPassword, newPassword);
    }

    // TODO : 아이디 찾기


    // TODO : 비밀번호 찾기



    @PostMapping("/station")
    public void addStation(
            @RequestParam(value = "memberId") Long memberId,
            @RequestParam(value = "csId") String csId)
    {
        memberService.addStation(memberId, csId);
    }
}


// 비밀번호 변경
// 회원가입
// 멤버삭제 (지우자)
// 중복회원 조회 (nickname)
// 아이디 찾기
// 비밀번호 찾기

// 충전소 별 리뷰추가(멥버의)
// 리뷰 수정
// 리뷰 지우기
// 리뷰 조회
// 나의 리뷰보기
// 충전소 조회

// 멤버별 즐겨찾기 조회
// 멤버 즐겨찾기 추가
// 멤버 즐겨찾기 제거


// (feign - netfilx)
// 환경별 DB 세팅 (로컬 / 라이브 구별)

// [지역(?) + 충전소타입] front 에서 검색 => was => front => naver

// 인천 + 타입 => 충전소들 리스트가 나오면 얘네들의 위도경도 네입ㅇ