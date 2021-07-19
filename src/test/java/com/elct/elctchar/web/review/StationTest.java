package com.elct.elctchar.web.review;

import com.elct.elctchar.web.station.domain.Charger;
import com.elct.elctchar.web.station.domain.ChargerRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@SpringBootTest
public class StationTest {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ChargerRepository chargerRepository;


    @BeforeEach
    void init() {
        Station station = stationRepository.save(new Station());
        String csId = "CsId";
        station.setCsId(csId);
    }

    @Test
    void stationCreateTest()
    {
        Station station = stationRepository.findStationByCsId("CsId");

        for (int i = 1; i <= 5; i++)
        {
            Charger charger = chargerRepository.save(new Charger());
            charger.addStation(station);
        }

        Assertions.assertThat(station.getStationId()).isNotNull();
    }

    @Test
    @Commit
    @DisplayName("충전소를 삭제하면 충전기 전체삭제되어야된다.")
    void deleteStationTest()
    {
        String csId = "csId";
        Station station = stationRepository.findStationByCsId(csId);

        for (int i = 1; i <= 5; i++)
        {
            Charger charger = chargerRepository.save(new Charger());
            charger.addStation(station);
        }

        Assertions.assertThat(station.getStationId()).isNotNull();
        chargerRepository.deleteAll(station.getChargerList());
        Station stationByCsId = stationRepository.findStationByCsId(csId);

//        Assertions.assertThat(stationByCsId).isNull();
    }
}
