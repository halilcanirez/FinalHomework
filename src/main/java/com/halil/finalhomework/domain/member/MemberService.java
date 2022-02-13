package com.halil.finalhomework.domain.member;

import com.halil.finalhomework.domain.port.MemberPersistencePort;
import com.halil.finalhomework.domain.port.RedisCachePort;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberPersistencePort memberPersistencePort;
    private final RedisCachePort redisCachePort;

    public Member createMember(Member newMember){
        return  memberPersistencePort.createMember(newMember);
    }

    public Member retrieveMember(Long identityNumber){
        Optional<Member> memberFromCache = redisCachePort.retrieveMember(identityNumber); //Optional.empty();
        if (memberFromCache.isEmpty()){
            System.out.println("db den geldi");
            Member retrievedMemberFromDB = memberPersistencePort.retrieveMemberByIdentityNumber(identityNumber);
            redisCachePort.createMember(retrievedMemberFromDB);
            return retrievedMemberFromDB;
        }
        System.out.println("cache den geldi");
        return memberFromCache.get();
    }

}
