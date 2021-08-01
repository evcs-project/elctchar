package com.elct.elctchar.web.station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationListSearchResponseDto {
    private List<StationDto> stationDtoList;
}
