package com.elct.elctchar.web.station.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class Charge {
    private String addr;
    private ChargeTp chargeTp;
    private String cpId;
    private String cpNm;
    private CpStat cpStat;
    private CpTp cpTp;
    @Column(unique = true)
    private String csId;
    private String csNm;
    private Double lat;
    private Double longi;

}
