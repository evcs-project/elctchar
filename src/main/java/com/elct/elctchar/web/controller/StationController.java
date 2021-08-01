package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.station.dto.StationInfoResponseDto;
import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationListSearchResponseDto;
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

    // TODO : 프론트 작업후 StationListSearchResponseDto 수정
    @ApiOperation(value = "검색한 충전기 타입을 가지고 있는 충전소 리스트 조회")
    @GetMapping("/search")
    public StationListSearchResponseDto searchStation(@Validated StationSearchRequestDto requestDto)
    {
        return stationService.searchStation(requestDto);
    }

    @ApiOperation(value = "충전소의 정보 및 리뷰 가져오기")
    @GetMapping("/search/{csId}")
    public StationInfoResponseDto getStationInfoByCsId(@PathVariable(value = "csId") String csId)
    {
        return stationService.getStationInfoByCsId(csId);
    }
}
