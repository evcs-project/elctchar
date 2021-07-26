package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.station.domain.Station;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StationDto {
    private String csId;
    private String addr;
    private String csNm;
    private Double lat;
    private Double lng;
    private List<ChargerDto> chargerDtoList;

    public static StationDto toDto(Station station)
    {
        return new StationDto(
                station.getCsId(),
                station.getAddr(),
                station.getCsNm(),
                station.getLat(),
                station.getLng(),
                station.getChargerList().stream().map(ChargerDto::toChargerDto).collect(Collectors.toList())
        );
    }
}
