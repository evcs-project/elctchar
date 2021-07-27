package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.review.dto.StaionReviewAddResponseDto;
import com.elct.elctchar.web.review.dto.StationReviewAddRequestDto;
import com.elct.elctchar.web.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void addReview(@RequestBody @Validated StationReviewAddRequestDto requestDto)
    {
        reviewService.addReview(requestDto);
    }
}
