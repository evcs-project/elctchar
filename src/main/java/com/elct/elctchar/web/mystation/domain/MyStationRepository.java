package com.elct.elctchar.web.mystation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyStationRepository extends JpaRepository<MyStation, MyStationId> {
    @Query("select ms from MyStation ms where ms.memberId.memberId = :memberId")
    List<MyStation> findByMemberId(Long memberId);
}
