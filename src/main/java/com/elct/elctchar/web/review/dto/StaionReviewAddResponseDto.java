package com.elct.elctchar.web.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaionReviewAddResponseDto {
    private List<ReviewDto> reviewDtoList;
}
