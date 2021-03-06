package com.elct.elctchar.web.review.domain;

import com.elct.elctchar.web.common.BaseEntity;
import com.elct.elctchar.web.member.domain.Member;
import com.elct.elctchar.web.station.domain.Station;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Review(String content, String title)
    {
        this.content = content;
        this.title=title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public static Review creteReview(String content,String title)
    {
        return new Review(content,title);
    }

    public void updateReview(String title,String content){
        this.title=title;
        this.content=content;
    }

    public void setMember(Member member)
    {
        member.getReviewList().add(this);
        this.member = member;
    }

    public void setStation(Station station)
    {
        station.getReviewList().add(this);
        this.station=station;
    }
}
