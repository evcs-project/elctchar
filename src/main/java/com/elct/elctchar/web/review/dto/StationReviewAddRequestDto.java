package com.elct.elctchar.web.review.dto;

import com.elct.elctchar.web.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationReviewAddRequestDto {

    @NotNull(message = "review 내용을 입력해주세요.")
    private @Valid ReviewRequestDto reviewDto;

    @NotBlank(message = "csId (충전소 ID)를 입력해주세요.")
    private String csId;

    public Review toEntity()
    {
        return  Review.creteReview(reviewDto.getContent(), reviewDto.getTitle());
    }
}
