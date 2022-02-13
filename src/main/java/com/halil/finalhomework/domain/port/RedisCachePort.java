package com.halil.finalhomework.domain.port;

import com.halil.finalhomework.domain.member.Member;

import java.util.Optional;

public interface RedisCachePort {

    Optional<Member> retrieveMember(Long identityNumber);

    void createMember(Member member);
}
