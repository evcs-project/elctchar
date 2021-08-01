package com.elct.elctchar.web.station;

import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.member.service.MemberService;
import com.elct.elctchar.web.mystation.domain.MyStation;
import com.elct.elctchar.web.station.domain.Charger;
import com.elct.elctchar.web.station.domain.ChargerRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import com.elct.elctchar.web.station.dto.StationInfoResponseDto;
import com.elct.elctchar.web.station.dto.StationSearchRequestDto;
import com.elct.elctchar.web.station.dto.StationListSearchResponseDto;
import com.elct.elctchar.web.station.dto.StationSearchType;
import com.elct.elctchar.web.station.service.StationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
public class StationTest {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StationService stationService;

    private static String testCsId = "TestCdId";
    private static String testMem = "TestMember";

    @BeforeEach
    void init()
    {
//        Station station = stationService.createStation(
//                testCsId, "TestAddr", "TestCsNm", 123.123, 123.123
//        );
//
//        Member member = memberService.createMember(testMem,"TestMemberPassword");
//        memberRepository.save(member);
//        for (int i = 1; i <= 3; i++)
//        {
//            Charger charger = chargerRepository.save(new Charger());
//            charger.addStation(station);
//        }
    }

    @Test
    @DisplayName("충전소를 삭제하면 해당 충전소의 충전기 전체가 삭제 되어야된다.")
    void stationCreateTest()
    {
        Station station = stationRepository.findStationByCsId(testCsId)
                .orElseThrow(()-> new IllegalArgumentException("Error"));

        List<Charger> beforeDelChargerList = chargerRepository.findChargerByCsId(testCsId);
        Assertions.assertThat(beforeDelChargerList).isNotEmpty();

        stationRepository.delete(station);

        Optional<Station> delStation = stationRepository.findStationByCsId(testCsId);
        Assertions.assertThat(delStation).isEmpty();

        List<Charger> afterDelChargerList = chargerRepository.findChargerByCsId(testCsId);
        Assertions.assertThat(afterDelChargerList).isEmpty();
    }

    @Test
    @DisplayName("나의 충전소(MyStation) 추가 테스트")
    void createStationTest()
    {
        Station station = stationRepository.findStationByCsId(testCsId)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_DATA));

        Member member = memberRepository.findMemberByNickname(testMem)
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_USER));

        MyStation myStation = memberService.addStation(member.getMemberId(), station.getCsId());

        Assertions.assertThat(myStation).isNotNull();
    }

    @Test
    @Sql({"classpath:station-test.sql"})
    @DisplayName("충전소 파라미터 (csNm, chargeTp) 조회 테스트")
    void searchStationTest()
    {
        StationSearchRequestDto requestDto = StationSearchRequestDto.builder()
                .csNm("cs_nm")
                .chargeTp(ChargeTp.STANDARD_CHARGE)
                .stationSearchType(StationSearchType.SEARCH_BY_CSNM)
                .build();

        StationListSearchResponseDto responseDto = stationService.searchStation(requestDto);

        Assertions.assertThat(responseDto.getStationDtoList()).hasSize(2);
    }

    @Test
    void searchStationReviewTest()
    {
        StationInfoResponseDto me000088 = stationService.getStationInfoByCsId("ME000088");
        System.out.println(me000088);
    }
}
