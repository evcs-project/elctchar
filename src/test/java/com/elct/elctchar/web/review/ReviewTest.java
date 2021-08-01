package com.elct.elctchar.web.review;

import com.elct.elctchar.web.auth.AuthUtil;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.dto.*;
import com.elct.elctchar.web.review.service.ReviewService;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@WithMockUser(username = "Test",roles = "USER")
public class ReviewTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    static String CsId="stCsId";
//
//    @BeforeEach
//    void init()
//    {
//        memberRepository.save(Member.newMember("1234","Test"));
//        Station station = new Station();
//        station.setCsId(CsId);
//        stationRepository.save(station);
//        Member member = Member.newMember("1234", "qwer1234");
//        memberRepository.save(member);
////        Long createReview = reviewService.createReview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
////        Long secondReviewContent = reviewService.createReview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
////        Long thirdReviewContent = reviewService.createReview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
////        Optional<Review> findReview = reviewRepository.findById(createReview);
//        Optional<Station> findStation = stationRepository.findStationByCsId(station.getCsId());
//        Optional<Member> findMember = memberRepository.findById(member.getMemberId());
//        Assertions.assertThat(findStation.get().getReviewList().size()).isEqualTo(3);
//        Assertions.assertThat(findMember.get().getReviewList().size()).isEqualTo(3);
////        for(Review r:findMember.get().getReviewList()){
////            System.out.println("r.getContent() = " + r.getContent());
////            System.out.println("r.getTitle() = " + r.getTitle());
////        }
////        for(Review r:findStation.get().getReviewList()){
////            System.out.println("r.getContent() = " + r.getContent());
////            System.out.println("r.getTitle() = " + r.getTitle());
////        }
//    }
//
//    @Test
//    void initReview()
//    {
//        String csId = "ME000079";
//
//        Member member = Member.newMember("123", "user01");
//        memberRepository.save(member);
//        Review review = Review.creteReview("content1", "안녕하세요.");
//        reviewRepository.save(review);
//
//
//    }
//
//    @Test
//    @DisplayName("리뷰를 달면 member와 station의 Reviewlist에 Review가 추가되어야한다")
//    void createreviewTest()
//    {
//        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
//                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
//        Station station = stationRepository.findStationByCsId(CsId)
//                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
//        List<Review> all = reviewRepository.findAll();
//        Assertions.assertThat(all).isEmpty();
//        Assertions.assertThat(member.getReviewList()).isEmpty();
//        Assertions.assertThat(station.getReviewList()).isEmpty();
//        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
//        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
//        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
//        Review review = reviewRepository.findById(firstReview)
//                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
//        Assertions.assertThat(review.getContent()).isEqualTo("firstreviewcontent");
//        Assertions.assertThat(member.getReviewList().size()).isEqualTo(3);
//        Assertions.assertThat(station.getReviewList().size()).isEqualTo(3);
//    }
//    @Test
//    @DisplayName("리뷰를 업데이트하면 작성한 member의 reviewlist와 station의 reviewlist모두 업데이트된다.")
//    void updateReviewTest()
//    {
////        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
////                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
////        Station station = stationRepository.findStationByCsId(CsId)
////                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
////        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
////        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
////        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
////        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewUpdateRequestDto.builder().title("updatesecondtitle").content("updatesecondcontent").build();
////        reviewService.updateReview(secondReview,reviewUpdateRequestDto);
////        Review review = reviewRepository.findById(secondReview)
////                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
////        Assertions.assertThat(member.getReviewList().get(1).getContent()).isEqualTo("updatesecondcontent");
////        Assertions.assertThat(member.getReviewList().get(1).getTitle()).isEqualTo("updatesecondtitle");
////        Assertions.assertThat(station.getReviewList().get(1).getContent()).isEqualTo("updatesecondcontent");
////        Assertions.assertThat(station.getReviewList().get(1).getTitle()).isEqualTo("updatesecondtitle");
////        Assertions.assertThat(review.getTitle()).isEqualTo("updatesecondtitle");
////        Assertions.assertThat(review.getContent()).isEqualTo("updatesecondcontent");
////        Station station = new Station();
////        station.setCsId("CsId2");
////        stationRepository.save(station);
////        Member member = Member.newMember("1234", "qwer1234");
////        memberRepository.save(member);
////        Long createreview = reviewService.createReview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
////        Long secondreviewcontent = reviewService.createReview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
////        Long thirdreviewcontent = reviewService.createReview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
////        reviewService.updateReview(secondreviewcontent,"updatesecondtitle","updatesecondcontent");
////        Optional<Review> findreview = reviewRepository.findById(createreview);
////        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
////        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
////        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(3);
////        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(3);
////        Assertions.assertThat(reviewRepository.findById(secondreviewcontent).get().getTitle()).isEqualTo("updatesecondtitle");
////        Assertions.assertThat(reviewRepository.findById(secondreviewcontent).get().getContent()).isEqualTo("updatesecondcontent");
//    }
//    @Test
//    @DisplayName("리뷰를 삭제하면 작성한member의 reivewlist와 station의 reviewlist모두 삭제된다.")
//    void deleteReviewTest()
//    {
////        Station station = new Station();
////        station.setCsId("CsId2");
////        stationRepository.save(station);
////        Member member = Member.newMember("1234", "qwer1234");
////        memberRepository.save(member);
////        Optional<Review> findreview = reviewRepository.findById(createreview);
////        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
////        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
////        reviewService.deleteReview(secondreviewcontent);
////        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(2);
////        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(2);
////        for(Review r:findmember.get().getReviewList()){
////            System.out.println("r.getContent() = " + r.getContent());
////            System.out.println("r.getTitle() = " + r.getTitle());
////        }
////        for(Review r:findstation.get().getReviewList()){
////            System.out.println("r.getContent() = " + r.getContent());
////            System.out.println("r.getTitle() = " + r.getTitle());
////        }
////        reviewService.deleteReview(createreview);
////        Assertions.assertThat(findstation.get().getReviewList().size()).isEqualTo(1);
////        Assertions.assertThat(findmember.get().getReviewList().size()).isEqualTo(1);
////
////        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
////                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
////        Station station = stationRepository.findStationByCsId(CsId)
////                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
////        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
////        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
////        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
////        Assertions.assertThat(member.getReviewList().size()).isEqualTo(3);
////        Assertions.assertThat(station.getReviewList().size()).isEqualTo(3);
////        reviewService.deleteReview(secondReview);
////        List<Review> all = reviewRepository.findAll();
////        Assertions.assertThat(all.size()).isEqualTo(2);
////        Assertions.assertThat(station.getReviewList().size()).isEqualTo(2);
////        Assertions.assertThat(member.getReviewList().size()).isEqualTo(2);
////        reviewService.deleteReview(firstReview);
////        Assertions.assertThat(station.getReviewList().size()).isEqualTo(1);
////        Assertions.assertThat(member.getReviewList().size()).isEqualTo(1);
//    }
//    @Test
//    void findMemberReviewTest()
//    {
//        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
//                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
//        Station station = stationRepository.findStationByCsId(CsId)
//                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
//        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
//        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
//        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
//        MemberReviewResponseDto memberReviewResponseDto = reviewService.findMemberReview();
//        Assertions.assertThat(memberReviewResponseDto.getReviewDtoList().size()).isEqualTo(3);
//        station.setCsId("CsId2");
//        stationRepository.save(station);
//        memberRepository.save(member);
//        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
//        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
//    }
//
//    @Test
//    void findStationReviewTest()
//    {
////        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
////                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
////        Station station = stationRepository.findStationByCsId(CsId)
////                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
////        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
////        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
////        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
////        StaionReviewAddResponseDto staionReviewAddResponseDto = reviewService.findStationReviewByCsId(CsId);
////        Assertions.assertThat(staionReviewAddResponseDto.getReviewDtoList().size()).isEqualTo(3);
////        Station station = new Station();
////        station.setCsId("CsId2");
////        stationRepository.save(station);
////        Member member = Member.newMember("1234", "qwer1234");
////        memberRepository.save(member);
////        Long createreview = reviewService.createReview(member.getNickname(), station.getCsId(), "firstreviewtitle", "firstreviewcontent");
////        Long secondreviewcontent = reviewService.createReview(member.getNickname(), station.getCsId(), "secondreviewtitle","secondreviewcontent");
////        Long thirdreviewcontent = reviewService.createReview(member.getNickname(), station.getCsId(), "thirdreviewtitle","thirdreviewcontent");
////        Optional<Review> findreview = reviewRepository.findById(createreview);
////        Optional<Station> findstation = stationRepository.findStationByCsId(station.getCsId());
////        Optional<Member> findmember = memberRepository.findById(member.getMemberId());
////        List<ReviewDto> findmyreview = reviewService.findstationreview(station.getCsId());
////        for(ReviewDto r:findmyreview){
////            System.out.println("r.getTitle() = " + r.getTitle());
////            System.out.println("r.getContent() = " + r.getContent());
////        }
////        Assertions.assertThat(findmyreview.size()).isEqualTo(3);
//    }

}
