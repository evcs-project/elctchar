package com.elct.elctchar.web.station.domain;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChargerRepository extends JpaRepository<Charger, Long> {

    @Query("select c from Charger c join c.station s where s.csId = :csId")
    List<Charger> findChargerByCsId(@Param("csId") String csId);
}
