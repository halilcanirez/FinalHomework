package com.halil.finalhomework.adapter.rest.member;

import com.halil.finalhomework.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class MemberResponse {

    private Long memberId;
    private Long memberIdentityNumber;
    private String memberName;
    private String memberSurname;
    private LocalDate memberBirthDate;

    public static MemberResponse convertToMemberResponse(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .memberIdentityNumber(member.getIdentityNumber())
                .memberName(member.getName())
                .memberSurname(member.getSurname()).
                memberBirthDate(member.getBirthDate())
                .build();

    }

}
