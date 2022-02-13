package com.halil.finalhomework.adapter.redis;

import com.halil.finalhomework.domain.member.Member;
import com.halil.finalhomework.domain.port.RedisCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisAdapter implements RedisCachePort {

    private final RedisTemplate<String,MemberCache> memberRedisTemplate;

    @Override
    public Optional<Member> retrieveMember(Long identityNumber) {
        MemberCache memberCache = memberRedisTemplate.opsForValue().get("finalhomework:member:" + identityNumber);
        return Optional.ofNullable(memberCache).map(MemberCache::convertToMember);
    }

    @Override
    public void createMember(Member member) {
        MemberCache memberCache = MemberCache.convertToMemberCache(member);
        memberRedisTemplate.opsForValue().set("finalhomework:member:"+member.getIdentityNumber(), memberCache, Duration.ofSeconds(30));
    }
}
