package com.elct.elctchar.web.review.service;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.dto.ReviewDto;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StationRepository stationRepository;

    public Long createreview(String nickname,String CsId,String title,String content){
        Station station = stationRepository.findStationByCsId(CsId).get();
        Member member = memberRepository.findMemberByNickname(nickname).get();
        Review review = Review.creteReview(content, title);
        review.setStation(station);
        review.setMember(member);
        reviewRepository.save(review);
        return review.getReviewId();
    }

    public void updatereview(Long id,String title,String content){
        Review review = reviewRepository.findById(id).get();
        review.updateReview(title,content);
    }
    public void deletereview(Long id){
        Review review = reviewRepository.findById(id).get();
        review.getMember().getReviewList().remove(review);
        review.getStation().getReviewList().remove(review);
        reviewRepository.delete(review);

    }
    public List<ReviewDto> findmemberreview(Long memberid){
        Member member = memberRepository.findById(memberid).get();
        List<ReviewDto> reviewDtoList=new ArrayList<>();
        for(Review r: member.getReviewList()){
            reviewDtoList.add(new ReviewDto(r.getTitle(),r.getContent()));
        }
        return reviewDtoList;
    }
    public List<ReviewDto> findstationreview(String CsId){
        Station station = stationRepository.findStationByCsId(CsId).get();
        List<ReviewDto> reviewDtoList=new ArrayList<>();
        for(Review r: station.getReviewList()){
            reviewDtoList.add(new ReviewDto(r.getTitle(),r.getContent()));
        }
        return reviewDtoList;
    }


}
