package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.station.domain.Charger;
import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import com.elct.elctchar.web.station.domain.cptype.CpStat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"cpId"})
public class ChargerDto {

    private Long chargerId;
    private String cpId;
    private String cpNm;
    private CpStat cpStat;
    private ChargeTp chargeTp;

    public static ChargerDto toChargerDto(Charger charger)
    {
        return new ChargerDto(
                charger.getChargerId(),
                charger.getCpId(),
                charger.getCpNm(),
                charger.getCpStat(),
                charger.getChargeTp()
        );
    }


}
