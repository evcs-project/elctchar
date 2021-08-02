package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.review.dto.*;
import com.elct.elctchar.web.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/review")
@Api(tags = "리뷰 관련 Api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("특정 충전소의 리뷰 리스트 가져오기")
    @GetMapping("/station/{csId}")
    public StaionReviewAddResponseDto getStationReviewsByCsId(@PathVariable(value = "csId") String csID)
    {
        return reviewService.findStationReviewByCsId(csID);
    }

    @ApiOperation("리뷰 쓰기")
    @PostMapping
    public void addReview(@RequestBody @Valid StationReviewAddRequestDto requestDto)
    {
        reviewService.addReview(requestDto);
    }

    @ApiOperation("리뷰 수정하기")
    @PutMapping("/{reviewid}")
    public void updateReview(@PathVariable(value = "reviewid") Long id, @RequestBody @Validated ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        reviewService.updateReview(id,reviewUpdateRequestDto);
    }

    @ApiOperation("리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(value = "reviewId") Long id)
    {
        reviewService.deleteReview(id);
    }

    @ApiOperation("특정유저의 리뷰 리스트 가져오기")
    @GetMapping("/myreview")
    public MemberReviewResponseDto getMemberReview()
    {
        return reviewService.findMemberReview();
    }
}
