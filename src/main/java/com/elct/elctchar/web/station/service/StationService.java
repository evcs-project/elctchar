package com.elct.elctchar.web.station.service;

import com.elct.elctchar.web.auth.AuthUtil;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.mystation.domain.MyStation;
import com.elct.elctchar.web.mystation.domain.MyStationRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.station.domain.Charger;
import com.elct.elctchar.web.station.domain.ChargerRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import com.elct.elctchar.web.station.dto.StationDto;
import com.elct.elctchar.web.station.dto.StationInfoResponseDto;
import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationListSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final ChargerRepository chargerRepository;
    private final MemberRepository memberRepository;
    private final MyStationRepository myStationRepository;

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
    public StationListSearchResponseDto searchStation(StationSearchRequestDto requestDto)
    {
        List<Station> stations = new ArrayList<>();
        switch (requestDto.getStationSearchType())
        {
            case SEARCH_BY_ADDR:
                stations = stationRepository.findChargerByAddrReqeustDto(
                        requestDto.getCsNm(),
                        requestDto.getChargeTp()
                );
                break;

            case SEARCH_BY_CSNM:
                stations = stationRepository.findChargerBySearchReqeustDto(
                        requestDto.getCsNm(),
                        requestDto.getChargeTp()
                );
                break;
        }

        List<StationDto> dtos = stations.stream()
                .map(StationDto::toDto)
                .distinct()
                .collect(Collectors.toList());

        return new StationListSearchResponseDto(dtos);
    }

    @Transactional(readOnly = true)
    public StationInfoResponseDto getStationInfoByCsId(String csId)
    {
        Station station = stationRepository.findStationByCsId(csId)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_DATA));

        List<Charger> chargerList = station.getChargerList();
        List<Review> reviewList = station.getReviewList();

        return new StationInfoResponseDto(station, chargerList, reviewList);
    }

    public List<StationDto> getMyStation()
    {
        System.out.println(AuthUtil.getCurUserNickName());
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_USER));

        List<MyStation> byMemberId = myStationRepository.findByMemberId(member.getMemberId());

        return byMemberId.stream()
                .map(s-> StationDto.toDto(s.getCsId()))
                .collect(Collectors.toList());

    }
}
