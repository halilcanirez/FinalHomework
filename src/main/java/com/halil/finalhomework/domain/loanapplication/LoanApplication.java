package com.halil.finalhomework.domain.loanapplication;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LoanApplication {
    private Long identityNumber;
    private String name;
    private String surname;
    private String phoneNumber;
    private Integer salary;
    private LocalDate birthDate;
}
