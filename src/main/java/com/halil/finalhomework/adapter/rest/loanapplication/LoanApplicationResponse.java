package com.halil.finalhomework.adapter.rest.loanapplication;

import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationResponse {

    private LoanApplicationStatus status;

    private Integer limit;

    private LocalDateTime createdDate;

    public static LoanApplicationResponse convertToLoanApplicationResponse(LoanApplicationResult result){
        LoanApplicationResponse response = new LoanApplicationResponse();
        response.setStatus(result.getStatus());
        response.setLimit(result.getLimit());
        response.setCreatedDate(result.getCreatedDate());
        return response;
    }
}
