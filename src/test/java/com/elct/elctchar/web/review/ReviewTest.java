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
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
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

    @BeforeEach
    void init()
    {
        memberRepository.save(Member.newMember("1234","Test"));
        Station station = new Station();
        station.setCsId(CsId);
        stationRepository.save(station);
    }


    @Test
    @DisplayName("리뷰를 달면 member와 station의 Reviewlist에 Review가 추가되어야한다")
    void createreviewTest()
    {
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        List<Review> all = reviewRepository.findAll();
        Assertions.assertThat(all).isEmpty();
        Assertions.assertThat(member.getReviewList()).isEmpty();
        Assertions.assertThat(station.getReviewList()).isEmpty();
        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
        Review review = reviewRepository.findById(firstReview)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Assertions.assertThat(review.getContent()).isEqualTo("firstreviewcontent");
        Assertions.assertThat(member.getReviewList().size()).isEqualTo(3);
        Assertions.assertThat(station.getReviewList().size()).isEqualTo(3);
    }
    @Test
    @DisplayName("리뷰를 업데이트하면 작성한 member의 reviewlist와 station의 reviewlist모두 업데이트된다.")
    void updateReviewTest()
    {
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
        ReviewUpdateRequestDto reviewUpdateRequestDto = ReviewUpdateRequestDto.builder().title("updatesecondtitle").content("updatesecondcontent").build();
        reviewService.updatereview(secondReview,reviewUpdateRequestDto);
        Review review = reviewRepository.findById(secondReview)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Assertions.assertThat(member.getReviewList().get(1).getContent()).isEqualTo("updatesecondcontent");
        Assertions.assertThat(member.getReviewList().get(1).getTitle()).isEqualTo("updatesecondtitle");
        Assertions.assertThat(station.getReviewList().get(1).getContent()).isEqualTo("updatesecondcontent");
        Assertions.assertThat(station.getReviewList().get(1).getTitle()).isEqualTo("updatesecondtitle");
        Assertions.assertThat(review.getTitle()).isEqualTo("updatesecondtitle");
        Assertions.assertThat(review.getContent()).isEqualTo("updatesecondcontent");
    }
    @Test
    @DisplayName("리뷰를 삭제하면 작성한member의 reivewlist와 station의 reviewlist모두 삭제된다.")
    void deleteReviewTest()
    {
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
        Assertions.assertThat(member.getReviewList().size()).isEqualTo(3);
        Assertions.assertThat(station.getReviewList().size()).isEqualTo(3);
        reviewService.deletereview(secondReview);
        List<Review> all = reviewRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(station.getReviewList().size()).isEqualTo(2);
        Assertions.assertThat(member.getReviewList().size()).isEqualTo(2);
        reviewService.deletereview(firstReview);
        Assertions.assertThat(station.getReviewList().size()).isEqualTo(1);
        Assertions.assertThat(member.getReviewList().size()).isEqualTo(1);
    }
    @Test
    void findMemberReviewTest()
    {
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
        MemberReviewResponseDto memberReviewResponseDto = reviewService.findmemberreview();
        Assertions.assertThat(memberReviewResponseDto.getReviewDtoList().size()).isEqualTo(3);
    }

    @Test
    void findStationReviewTest()
    {
        Member member = memberRepository.findMemberByNickname(AuthUtil.getCurUserNickName())
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Long firstReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("firstreviewtitle","firstreviewcontent"),CsId));
        Long secondReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("secondreviewtitle","secondreviewcontent"),CsId));
        Long thirdReview = reviewService.addReview(new StationReviewAddRequestDto(new ReviewDto("thirdreviewtitle","thirdreviewcontent"),CsId));
        StaionReviewAddResponseDto staionReviewAddResponseDto = reviewService.findStationReviewByCsId(CsId);
        Assertions.assertThat(staionReviewAddResponseDto.getReviewDtoList().size()).isEqualTo(3);
    }

}
