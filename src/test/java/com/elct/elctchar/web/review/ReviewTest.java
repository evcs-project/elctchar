package com.elct.elctchar.web.review;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.dto.ReviewDto;
import com.elct.elctchar.web.review.service.ReviewService;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
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
        Long createreview = reviewService.createreview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
        Long secondreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
        Long thirdreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
        Optional<Review> findreview = reviewRepository.findById(createreview);
        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(3);
        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(3);
        for(Review r:findmember.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
        for(Review r:findstation.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
    }
    @Test
    void updateReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);
        Member member = Member.newMember("1234", "qwer1234");
        memberRepository.save(member);
        Long createreview = reviewService.createreview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
        Long secondreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
        Long thirdreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
        reviewService.updatereview(secondreviewcontent,"updatesecondtitle","updatesecondcontent");
        Optional<Review> findreview = reviewRepository.findById(createreview);
        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(3);
        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(3);
        Assertions.assertThat(reviewRepository.findById(secondreviewcontent).get().getTitle()).isEqualTo("updatesecondtitle");
        Assertions.assertThat(reviewRepository.findById(secondreviewcontent).get().getContent()).isEqualTo("updatesecondcontent");
        for(Review r:findmember.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
        for(Review r:findstation.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
    }
    @Test
    void deleteReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);
        Member member = Member.newMember("1234", "qwer1234");
        memberRepository.save(member);
        Long createreview = reviewService.createreview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
        Long secondreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
        Long thirdreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
        Optional<Review> findreview = reviewRepository.findById(createreview);
        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
        reviewService.deletereview(secondreviewcontent);
        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(2);
        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(2);
        for(Review r:findmember.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
        for(Review r:findstation.get().getReviewList()){
            System.out.println("r.getContent() = " + r.getContent());
            System.out.println("r.getTitle() = " + r.getTitle());
        }
        reviewService.deletereview(createreview);
        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(1);
        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(1);

    }
    @Test
    void findMemberReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);
        Member member = Member.newMember("1234", "qwer1234");
        memberRepository.save(member);
        Long createreview = reviewService.createreview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
        Long secondreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
        Long thirdreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
        Optional<Review> findreview = reviewRepository.findById(createreview);
        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
        List<ReviewDto> findmyreview = reviewService.findmemberreview(member.getMemberId());
        for(ReviewDto r:findmyreview){
            System.out.println("r.getTitle() = " + r.getTitle());
            System.out.println("r.getContent() = " + r.getContent());
        }
        Assertions.assertThat(findmyreview.size()).isEqualTo(3);
    }

    @Test
    void findStationReviewTest()
    {
        Station station = new Station();
        station.setCsId("CsId2");
        stationRepository.save(station);
        Member member = Member.newMember("1234", "qwer1234");
        memberRepository.save(member);
        Long createreview = reviewService.createreview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
        Long secondreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
        Long thirdreviewcontent = reviewService.createreview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
        Optional<Review> findreview = reviewRepository.findById(createreview);
        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
        List<ReviewDto> findmyreview = reviewService.findstationreview(station.getCsId());
        for(ReviewDto r:findmyreview){
            System.out.println("r.getTitle() = " + r.getTitle());
            System.out.println("r.getContent() = " + r.getContent());
        }
        Assertions.assertThat(findmyreview.size()).isEqualTo(3);
    }

}
