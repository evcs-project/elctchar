package com.elct.elctchar.web.station.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/station")
public class StationController {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Dto{
        private Double lng;
        private Double lat;

        public Dto(Double lng, Double lat) {
            this.lng = lng;
            this.lat = lat;
        }
    }

    @GetMapping("/mock")
    public List<Dto> get(){
        List<Dto> dtos = new ArrayList<>();
        dtos.add(new Dto(37.3595704, 127.105399));
        dtos.add(new Dto(37.3605704, 127.105399));
       return dtos;
    }
}
