package com.elct.elctchar.web.mystation.domain;

import com.elct.elctchar.web.common.BaseEntity;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.review.domain.Review;
import com.elct.elctchar.web.station.domain.Station;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "my_station")
@Setter
@IdClass(MyStationId.class)
public class MyStation extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_id")
    private Station csId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;
}
