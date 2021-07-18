package com.elct.elctchar.web.auth;

import com.elct.elctchar.config.jwt.JwtFilter;
import com.elct.elctchar.config.jwt.TokenProvider;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.member.dto.LoginDto;
import com.elct.elctchar.web.member.dto.TokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    public AuthController(
            TokenProvider tokenProvider
            , AuthenticationManagerBuilder authenticationManagerBuilder
            , MemberRepository memberRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

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
                        ()->  new GlobalApiException(ErrorCode.NONE_USER)
                );
        return new UserInfoResponseDto(userDetails.getUsername(), member.getNickname(), member.getAuthorities());
    }

}