package com.elct.elctchar.web.station.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 충전기 타입
 */
@Getter
@AllArgsConstructor
public enum ChargeTp {

    STANDARD_CHARGE("완속 충전"),
    QUICK_CHARGE("급속 충전");

    private String name;
}
