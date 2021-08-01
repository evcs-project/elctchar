package com.elct.elctchar.web.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ROLETYPE {
    ADMIN("ADMIN","관리자"),
    USER("USER","일반유저");
    private String name;
    private String expl;
}
