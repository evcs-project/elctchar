package com.elct.elctchar.web.station.domain;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query("select s from Station s where s.csId =:csId")
    Optional<Station> findStationByCsId(String csId);

    @Query("select s from Charger c join c.station s where s.csNm like :search% and c.chargeTp = :chargeTp")
    List<Station> findChargerBySearchReqeustDto(@Param("search") String search, @Param("chargeTp") ChargeTp chargeTp);
}
