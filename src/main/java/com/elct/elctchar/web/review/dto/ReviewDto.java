package com.elct.elctchar.web.review.dto;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.review.domain.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String title;
    private String content;
    private Long memberId;
    private String nickName;
    private LocalDateTime regDt;

    public ReviewDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public static ReviewDto toReviewDto(Review review)
    {
        Member member = review.getMember();

        return ReviewDto.builder()
                .content(review.getContent())
                .title(review.getTitle())
                .nickName(member.getNickname())
                .memberId(member.getMemberId())
                .regDt(review.getCreatedDate())
                .build();
    }
}
