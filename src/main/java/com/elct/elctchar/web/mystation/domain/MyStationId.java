package com.elct.elctchar.web.mystation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class MyStationId implements Serializable {
    private Long memberId;
    private Long stationId;
}
