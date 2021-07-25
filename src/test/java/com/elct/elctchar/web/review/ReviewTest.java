package com.elct.elctchar.web.review;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.service.ReviewService;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Test
    void createReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);
        Member member = Member.newMember("1234", "qwer1234");
        memberRepository.save(member);
        Long createreview = reviewService.createreview(member.getMemberId(), station.getCsId(), "reviewtitle", "reviewcontent");
        Optional<Review> byId = reviewRepository.findById(createreview);
        Assertions.assertThat(member.getReviewList().size()).isEqualTo(1);
        Assertions.assertThat(station.getReviewList().size()).isEqualTo(1);
        Assertions.assertThat(byId.get().getContent()).isEqualTo("reviewcontent");
        Assertions.assertThat(byId.get().getTitle()).isEqualTo("reviewtitle");
    }

}
