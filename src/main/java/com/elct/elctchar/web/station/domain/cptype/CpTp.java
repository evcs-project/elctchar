package com.elct.elctchar.web.station.domain.cptype;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 충전 방식
 */
@Getter
@AllArgsConstructor
public enum CpTp {
    B_TYPE("B타입(5핀)"),
    C_TYPE("C타입(5핀)"),
    BC_TYPE_5("BC타입(5핀)"),
    BC_TYPE_7("BC타입(7핀)"),
    DC_CHADEMO("DC차데모"),
    AC3("AC3상"),
    DC_COMBO("DC콤보"),
    DC_CHADEMO_COMBO("DC차데모+콤보");

    private String name;
}
