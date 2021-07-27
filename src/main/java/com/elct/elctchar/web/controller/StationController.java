package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationSearchResponseDto;
import com.elct.elctchar.web.station.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@Api(tags = "충전소 관련 Api")
@RequestMapping("/api/station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @ApiOperation(value = "검색한 충전기 타입을 가지고 있는 충전소 리스트 조회")
    @GetMapping("/search")
    public StationSearchResponseDto searchStation(@Validated StationSearchRequestDto requestDto)
    {
        return stationService.searchStation(requestDto);
    }
}
