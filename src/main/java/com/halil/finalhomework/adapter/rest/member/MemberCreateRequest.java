package com.halil.finalhomework.adapter.rest.member;

import com.halil.finalhomework.domain.member.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@Getter
public class MemberCreateRequest {

    private Long identityNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String telephoneNumber;

    public Member convertToMember(){
        return Member.builder()
                .identityNumber(identityNumber)
                .name(name)
                .surname(surname)
                .birthDate(birthDate)
                .telephoneNumber(telephoneNumber)
                .build();
    }
}
