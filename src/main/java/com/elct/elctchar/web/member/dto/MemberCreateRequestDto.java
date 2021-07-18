package com.elct.elctchar.web.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberCreateRequestDto {
    private String username;
    private String password;
}
