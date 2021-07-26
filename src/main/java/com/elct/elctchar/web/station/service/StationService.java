package com.elct.elctchar.web.station.service;

import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.station.domain.ChargerRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import com.elct.elctchar.web.station.dto.StationDto;
import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final ChargerRepository chargerRepository;

    @Transactional
    public Station createStation(String csId, String addr, String csNm, Double lat, Double lng)
    {
        Optional<Station> stationByCsId = stationRepository.findStationByCsId(csId);

        if (stationByCsId.isPresent())
        {
            throw new GlobalApiException(ErrorCode.DUPLICATE_PARAMETER);
        }

        Station station = Station.createStation(csId, addr, csNm, lat, lng);
        return stationRepository.save(station);
    }

    // TODO : Query dsl 전환하여 충전기도 같이 포함처리 (메모리 관련)
    @Transactional(readOnly = true)
    public StationSearchResponseDto searchStation(StationSearchRequestDto requestDto)
    {
        List<Station> stations = stationRepository.findChargerBySearchReqeustDto(
                requestDto.getSearch(),
                requestDto.getChargeTp()
        );

        List<StationDto> dtos = stations.stream()
                .map(StationDto::toDto)
                .collect(Collectors.toList());

        return new StationSearchResponseDto(dtos);
    }
}
