package com.elct.elctchar.web.auth;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserInfoResponseDto {
    private String memberId;
    private String nickname;
    private String token;
    private String role;

    public UserInfoResponseDto(String memberId, String nickname, Set<Authority> role)
    {
        this.memberId = memberId;
        this.nickname = nickname;
        this.role = role.isEmpty() ? "" : role.stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(" "));
    }
}
