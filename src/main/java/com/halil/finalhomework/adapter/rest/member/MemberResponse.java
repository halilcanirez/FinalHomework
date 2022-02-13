package com.halil.finalhomework.adapter.rest.member;

import com.halil.finalhomework.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberResponse {

    private Long memberId;
    private String memberName;
    private String memberSurname;

    public static MemberResponse convertToMemberResponse(Member member){
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(member.getId());
        memberResponse.setMemberName(member.getName());
        memberResponse.setMemberSurname(member.getSurname());
        return memberResponse;
    }

}
