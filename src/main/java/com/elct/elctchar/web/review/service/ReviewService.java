package com.elct.elctchar.web.review.service;

import com.elct.elctchar.web.auth.AuthUtil;
import com.elct.elctchar.web.exception.ErrorCode;
import com.elct.elctchar.web.exception.GlobalApiException;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.member.domain.MemberRepository;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.dto.*;
import com.elct.elctchar.web.station.domain.Station;
import com.elct.elctchar.web.station.domain.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StationRepository stationRepository;

    @Transactional
    public Long createreview(String CsId,String title,String content)
    {
        String nickName = AuthUtil.getCurUserNickName();
        Station station = stationRepository.findStationByCsId(CsId)
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_DATA));
        Member member = memberRepository.findMemberByNickname(nickName)
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Review review = Review.creteReview(content, title);
        review.setStation(station);
        review.setMember(member);
        reviewRepository.save(review);
        return review.getReviewId();
    }
   @Transactional
    public void updatereview(Long id,ReviewUpdateRequestDto dto)
   {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_DATA));
        review.updateReview(dto.getTitle(),dto.getContent());
    }
    @Transactional
    public void deletereview(Long id)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new GlobalApiException(ErrorCode.NONE_DATA));
        review.getMember().getReviewList().remove(review);
        review.getStation().getReviewList().remove(review);
        reviewRepository.delete(review);

    }
    @Transactional(readOnly = true)
    public MemberReviewResponseDto findmemberreview()
    {
        String userNickName = AuthUtil.getCurUserNickName();
        Member member = memberRepository.findMemberByNickname(userNickName)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_USER));
        List<ReviewDto> collect = member.getReviewList().stream()
                .map(ReviewDto::toReviewDto).collect(Collectors.toList());
        return new MemberReviewResponseDto(collect);
    }

    @Transactional(readOnly = true)
    public StaionReviewAddResponseDto findStationReviewByCsId(String csId)
    {
        List<Review> reviewsByCsId = reviewRepository.findReviewsByCsId(csId);

        List<ReviewDto> reviewDtoList = reviewsByCsId.stream()
                .map(ReviewDto::toReviewDto)
                .collect(Collectors.toList());

        return new StaionReviewAddResponseDto(reviewDtoList);
    }

    @Transactional
    public Long addReview(StationReviewAddRequestDto requestDto)
    {
        String userNickName = AuthUtil.getCurUserNickName();

        Member member = memberRepository.findMemberByNickname(userNickName)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_USER));

        Station station = stationRepository.findStationByCsId(requestDto.getCsId())
                .orElseThrow(()-> new GlobalApiException(ErrorCode.NONE_DATA));

        Review review = requestDto.toEntity();
        review.setStation(station);
        review.setMember(member);
        reviewRepository.save(review);
        return review.getReviewId();
    }
}
