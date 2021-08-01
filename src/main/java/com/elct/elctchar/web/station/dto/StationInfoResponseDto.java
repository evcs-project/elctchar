package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.review.dto.ReviewDto;
import com.elct.elctchar.web.station.domain.Charger;
import com.elct.elctchar.web.station.domain.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class StationInfoResponseDto {

    private List<ReviewDto> reviewDtos;
    private List<ChargerDto> chargerDtos;
    private StationDto stationDto;

    public StationInfoResponseDto(Station station, List<Charger> chargerList, List<Review> reviewList)
    {
        this.reviewDtos = reviewList.stream()
                .map(ReviewDto::toReviewDto)
                .collect(Collectors.toList());
        this.chargerDtos = chargerList.stream()
                .map(ChargerDto::toChargerDto)
                .collect(Collectors.toList());
        this.stationDto = StationDto.toDto(station);
    }
}
