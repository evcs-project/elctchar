package com.elct.elctchar.web.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberCreateRequestApiDto {
    private String username;
    private String password;

}
