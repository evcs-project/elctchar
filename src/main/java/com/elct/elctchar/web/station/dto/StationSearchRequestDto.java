package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StationSearchRequestDto {
    private String search;
    private ChargeTp chargeTp;
}
