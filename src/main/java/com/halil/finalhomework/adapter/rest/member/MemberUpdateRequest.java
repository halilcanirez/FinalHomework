package com.halil.finalhomework.adapter.rest.member;

import com.halil.finalhomework.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Builder
public class MemberUpdateRequest {

    @NotNull
    private Long id;
    @NotNull
    private Long memberIdentityNumber;
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberSurname;
    @NotNull
    private LocalDate memberBirthDate;
    @NotNull
    private String telephoneNumber;

    Member convertToMember(){
        return Member.builder()
                .id(id)
                .identityNumber(memberIdentityNumber)
                .name(memberName)
                .surname(memberSurname)
                .birthDate(memberBirthDate)
                .telephoneNumber(telephoneNumber)
                .build();

    }
}
