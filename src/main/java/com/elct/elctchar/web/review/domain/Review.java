package com.elct.elctchar.web.review.domain;

import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.station.domain.Station;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Review(String content)
    {
        this.content = content;
    }

    public static Review creteReview(String content)
    {
        return new Review(content);
    }

    public void setMember(Member member)
    {
        member.getReviewList().add(this);
        this.member = member;
    }

    public void setStation(Station station) {
        station.getReviewList().add(this);
        this.station=station;
    }
}
