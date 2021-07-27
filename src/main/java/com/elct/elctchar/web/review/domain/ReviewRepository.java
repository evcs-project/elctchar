package com.elct.elctchar.web.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r join r.station s where s.csId = :csId")
    List<Review> findReviewsByCsId(@Param("csId") String csId);
}
