package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationSearchResponseDto;
import com.elct.elctchar.web.station.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@CrossOrigin("*")
@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping("/search")
    public StationSearchResponseDto searchStation(StationSearchRequestDto requestDto)
    {
        return stationService.searchStation(requestDto);
    }
}
