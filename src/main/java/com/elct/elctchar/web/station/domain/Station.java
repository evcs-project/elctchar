package com.elct.elctchar.web.station.domain;

import com.elct.elctchar.web.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "station")
@Getter
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;
    private String addr;

    @Column(unique = true)
    private String csId;
    @Column(unique = true)
    private String csNm;
    private Double lat;
    private Double longi;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    List<Review> reviewList=new ArrayList<>();

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    List<Charger> chargerList=new ArrayList<>();

    public void setCsId(String csId) {
        this.csId = csId;
    }
}
