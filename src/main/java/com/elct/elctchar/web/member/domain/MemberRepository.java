package com.elct.elctchar.web.member.domain;

import com.elct.elctchar.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
