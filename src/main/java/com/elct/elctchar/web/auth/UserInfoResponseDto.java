package com.elct.elctchar.web.auth;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserInfoResponseDto {
    private Long memberId;
    private String nickname;
    private String token;
    private String role;

    public UserInfoResponseDto(Long memberId, String nickname, String token, String role)
    {
        this.memberId = memberId;
        this.nickname = nickname;
        this.token = token;
        this.role = role;
    }

    public UserInfoResponseDto(Long memberId, String nickname, Set<Authority> role)
    {
        this.memberId = memberId;
        this.nickname = nickname;
        this.role = role.isEmpty() ? "" : role.stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(" "));
    }
}
