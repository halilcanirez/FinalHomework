package com.halil.finalhomework.adapter.redis;

import com.halil.finalhomework.domain.member.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCache {

    private Long id;

    private Long identityNumber;

    private String name;

    private String surname;

    private String telephoneNumber;

    public static MemberCache convertToMemberCache(Member member){
        return MemberCache.builder()
                .id(member.getId())
                .identityNumber(member.getIdentityNumber())
                .name(member.getName())
                .surname(member.getSurname())
                .telephoneNumber(member.getTelephoneNumber())
                .build();
    }

    public Member convertToMember(){
        return Member.builder()
                .id(id)
                .identityNumber(identityNumber)
                .name(name)
                .surname(surname)
                .telephoneNumber(telephoneNumber)
                .build();
    }
}
