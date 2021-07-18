package com.elct.elctchar.web.member.service;

import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(
            MemberRepository memberRepository
            , PasswordEncoder passwordEncoder)
    {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(String username, String password)
    {
        String encodedPassword = passwordEncoder.encode(password);
        Member member = Member.newMember(encodedPassword, username);

        return memberRepository.save(member);
    }

    public void deleteMember(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));

        memberRepository.delete(member);
    }

    public void changePassword(Long memberId, String currentPassword, String newPassword)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->  new GlobalApiException(ErrorCode.NONE_DATA));

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        member.changePassword(member.getPassword(), encodedNewPassword);

    }

}
