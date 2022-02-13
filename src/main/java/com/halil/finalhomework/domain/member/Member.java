package com.halil.finalhomework.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
@Setter
public class Member {

    private Long id;

    private Long identityNumber;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String telephoneNumber;
}
