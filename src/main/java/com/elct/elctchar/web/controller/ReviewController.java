package com.elct.elctchar.web.controller;

import com.elct.elctchar.web.review.domain.ReviewRepository;
import com.elct.elctchar.web.review.dto.ReviewDto;
import com.elct.elctchar.web.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO  : Rest controller 전환
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/myreview")
    public String myreview(Long id, Model model){
        List<ReviewDto> reviewDtoList = reviewService.findmemberreview(id);
        model.addAttribute("reviewlist",reviewDtoList);
        return "myreview";
    }
    @GetMapping("/stationreview")
    public String stationreview(String CsId,Model model){
        List<ReviewDto> reviewDtoList = reviewService.findstationreview(CsId);
        model.addAttribute("reviewlist",reviewDtoList);
        return "station";
    }
    @PostMapping("/registreview")
    public String registereview(String CsId,String nickname,String title,String content){
        reviewService.createreview(nickname,CsId,title,content);
        return "/";
    }
    @PutMapping("/updatereview")
    public String updatereview(Long id,String title,String content){
        reviewService.updatereview(id,title,content);
        return "/";
    }
    @DeleteMapping("/deletereview")
    public String deletereview(Long id){
        reviewService.deletereview(id);
        return "/";
    }

}
