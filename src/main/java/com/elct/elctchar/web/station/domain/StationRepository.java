package com.elct.elctchar.web.station.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query("select s from Station s where s.csId =:csId")
    Optional<Station> findStationByCsId(String csId);
}
