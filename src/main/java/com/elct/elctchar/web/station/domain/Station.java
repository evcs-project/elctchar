package com.elct.elctchar.web.station.domain;

import com.elct.elctchar.web.common.BaseEntity;
import com.elct.elctchar.web.review.domain.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "station")
@Getter
@Setter
@Entity
@ToString(exclude = {"chargerList", "reviewList"})
public class Station extends BaseEntity {

    @Id
    @Column(name = "cs_id")
    private String csId;

    private String addr;

    @Column(unique = true)
    private String csNm;
    private Double lat;
    private Double lng;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Review> reviewList=new ArrayList<>();

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Charger> chargerList=new ArrayList<>();

    private Station(String csId, String addr, String csNm, Double lat, Double lng) {
        this.csId = csId;
        this.addr = addr;
        this.csNm = csNm;
        this.lat = lat;
        this.lng = lng;
    }

    public static Station createStation(String csId, String addr, String csNm, Double lat, Double lng)
    {
        return new Station(csId, addr, csNm, lat, lng);
    }

}
