package com.elct.elctchar.web.review.service;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StationRepository stationRepository;

    public Long createreview(Long Memberid,String stationid,String title,String content){
        Station station = stationRepository.findStationByCsId(stationid).get();
        Member member = memberRepository.findById(Memberid).get();
        Review review = Review.creteReview(content, title);
        review.setStation(station);
        review.setMember(member);
        reviewRepository.save(review);
        return review.getReviewId();
    }


}
