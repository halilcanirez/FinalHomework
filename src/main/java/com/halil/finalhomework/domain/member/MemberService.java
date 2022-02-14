package com.halil.finalhomework.domain.member;

import com.halil.finalhomework.domain.exception.ExceptionType;
import com.halil.finalhomework.domain.exception.FinalHomeworkValidationException;
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
        if (Boolean.TRUE.equals(memberPersistencePort.isIdentityNumberAlreadyExist(newMember.getIdentityNumber()))){
            throw new FinalHomeworkValidationException(ExceptionType.IDENTITY_NUMBER_EXISTS);
        }
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

    public Member updateMember(Member member) {
        return memberPersistencePort.updateMemberById(member);
    }

    public void deleteMember(Long memberId){
        memberPersistencePort.updateStatusById(memberId);
    }
}
