package com.elct.elctchar.web.review.controller;

import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


}
