package com.elct.elctchar.web.station.domain.cptype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CpStat {
    RE_CHARGEABLE("충전가능"),
    CHARGING("충전 중"),
    BROKEN_CHECK("고장/점검"),
    COMMU_FAILURE("통신 장애"),
    DISCONNECTED_COMMU("통신 미연결");

    private String name;
}
