package com.halil.finalhomework.domain.loanapplication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class LoanApplicationResult {

    private LoanApplicationStatus status;
    private Integer limit;
    private LocalDateTime createdDate;
}
