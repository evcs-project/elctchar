package com.elct.elctchar.web.member.service;

import com.elct.elctchar.web.auth.AuthUtil;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.mystation.domain.MyStation;
import com.elct.elctchar.web.mystation.domain.MyStationRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final StationRepository stationRepository;
    private final MyStationRepository myStationRepository;
    @Autowired
    public MemberService(
            MemberRepository memberRepository
            , PasswordEncoder passwordEncoder, StationRepository stationRepository, MyStationRepository myStationRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.stationRepository = stationRepository;
        this.myStationRepository = myStationRepository;
    }

    @Transactional
    public Member createMember(String nickName, String password)
    {
        checkDuplicateMember(nickName);
        String encodedPassword = passwordEncoder.encode(password);
        Member member = Member.newMember(encodedPassword, nickName);

        return memberRepository.save(member);
    }

    public void deleteMember(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));

        memberRepository.delete(member);
    }

    public void changePassword(Long memberId, String newPassword)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        if (passwordEncoder.matches(newPassword, member.getPassword()))
        {
            throw new GlobalApiException(ErrorCode.PASSWORD_EQUAL_ERROR);
        }
        member.changePassword(member.getPassword(), encodedNewPassword);

    }

    public void checkDuplicateMember(String nickName)
    {
        Optional<Member> memberByNickname = memberRepository.findMemberByNickname(nickName);

        if (memberByNickname.isPresent())
        {
            throw new GlobalApiException(ErrorCode.DUPLICATE_USER);
        }
    }

    @Transactional
    public MyStation addStation(String csId)
    {
        String userNickName = AuthUtil.getCurUserNickName();
        Member member = memberRepository.findMemberByNickname(userNickName)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_USER));

        Station station = stationRepository.findStationByCsId(csId)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_DATA));

        MyStation myStation = new MyStation();
        myStation.setMemberId(member);
        myStation.setCsId(station);
        myStationRepository.save(myStation);

        return myStation;

    }
}