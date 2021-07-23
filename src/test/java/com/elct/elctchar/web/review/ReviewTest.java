package com.elct.elctchar.web.review;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void createReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);

        Review review = new Review();
        review.setContent("content");
        reviewRepository.save(review);
        review.setStation(station);
    }

}
