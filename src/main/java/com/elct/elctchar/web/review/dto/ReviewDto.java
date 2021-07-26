package com.elct.elctchar.web.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    String title;
    String content;

    public ReviewDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
