package com.elct.elctchar.web.member.domain;

import com.elct.elctchar.web.auth.Authority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long memberId;

    @Column(name = "password", length = 80)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public static Member createMember(String password, String nickname)
    {
        return new Member(password, nickname);
    }

    private Member(String password, String nickname)
    {
        this.password = password;
        this.nickname = nickname;
    }

    public void changePassword(String oldPassword, String newPassword)
    {
        if (oldPassword.matches(newPassword))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        setPassword(newPassword);
    }

    private void setPassword(String newPassword)
    {
        if (newPassword.isEmpty())
            throw new IllegalArgumentException("새로운 비밀번호가 없습니다.");

        this.password = newPassword;
    }

}
