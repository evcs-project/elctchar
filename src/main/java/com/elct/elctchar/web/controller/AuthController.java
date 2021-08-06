package com.elct.elctchar.web.controller;

import com.elct.elctchar.config.jwt.JwtFilter;
import com.elct.elctchar.config.jwt.TokenProvider;
import com.elct.elctchar.web.auth.UserInfoResponseDto;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.member.dto.LoginDto;
import com.elct.elctchar.web.member.dto.TokenDto;
import com.elct.elctchar.web.member.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@Api(tags = "로그인 관련 Api")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;

    public AuthController(
            TokenProvider tokenProvider
            , AuthenticationManagerBuilder authenticationManagerBuilder
            , MemberRepository memberRepository)
    {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Validated @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getNickName(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt, loginDto.getNickName()), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/log")
    public UserInfoResponseDto isLogin(Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberRepository.findMemberByNickname(userDetails.getUsername())
                .orElseThrow(
                        ()->  new GlobalApiException(ErrorCode.NONE_USER));
        return new UserInfoResponseDto(member.getMemberId(), member.getNickname(), member.getAuthorities());
    }

}