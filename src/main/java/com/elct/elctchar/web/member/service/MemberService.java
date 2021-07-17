package com.elct.elctchar.web.member.service;

import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public Member createMember(String username, String password)
    {
        Member member = Member.createMember(password, username);
        return memberRepository.save(member);
    }

    public void deleteMember(Long memberId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NON_DATA));

        memberRepository.delete(member);
    }

    public void changePassword(Long memberId, String currentPassword, String newPassword)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NON_DATA));

        member.changePassword(currentPassword, newPassword);

    }
}
