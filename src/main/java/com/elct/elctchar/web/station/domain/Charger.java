package com.elct.elctchar.web.station.domain;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import com.elct.elctchar.web.station.domain.cptype.CpStat;
import com.elct.elctchar.web.station.domain.cptype.CpTp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "charger")
@Entity
public class Charger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cp_id")
    private Long cpId;

    @Column(name = "cp_nm")
    private String cpNm;

    @Column(name = "cp_stat")
    @Enumerated(EnumType.STRING)
    private CpStat cpStat;

    @Column(name = "charge_tp")
    @Enumerated(EnumType.STRING)
    private ChargeTp chargeTp;

    @Column(name = "cp_tp")
    @Enumerated(EnumType.STRING)
    private CpTp cpTp;

    @Column(name = "stat_update_datetime")
    private LocalDateTime statUpdateDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    public void addStation(Station station)
    {
        this.station = station;
        station.getChargerList().add(this);
    }
}
