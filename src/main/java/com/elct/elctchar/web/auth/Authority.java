package com.elct.elctchar.web.auth;

import com.elct.elctchar.web.member.domain.Member;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "authority")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@EqualsAndHashCode(of = "authorityName")
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    public static void addRole(Member member, ROLETYPE ROLETYPE)
    {
        Set<Authority> authorities = member.getAuthorities();
        authorities.add(new Authority(ROLETYPE.getName()));
        member.setAuthorities(authorities);
    }
}