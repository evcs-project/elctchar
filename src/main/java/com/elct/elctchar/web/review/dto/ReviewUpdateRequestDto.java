package com.elct.elctchar.web.review.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewUpdateRequestDto {
    @NotNull(message = "review 제목을 입력해주세요.")
    private String title;

    @NotNull(message = "review 내용을 입력해주세요.")
    private String content;

}
