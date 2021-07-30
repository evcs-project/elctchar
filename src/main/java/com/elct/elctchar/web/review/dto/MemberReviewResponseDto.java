package com.elct.elctchar.web.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberReviewResponseDto {
    private List<ReviewDto> reviewDtoList;
}
