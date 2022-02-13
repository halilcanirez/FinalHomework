package com.halil.finalhomework.domain.loanapplication.applicationrules;

import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationProperties;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("LoanApplicationFourthRule")
@RequiredArgsConstructor
public class LoanApplicationFourthRule implements LoanApplicationRules{

    @Override
    public LoanApplicationResult calculateLoanApplicationResult(LoanApplication loanApplication) {
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.ACCEPTED);
        loanApplicationResult.setLimit(loanApplication.getSalary()*4);
        loanApplicationResult.setCreatedDate(LocalDateTime.now());
        return loanApplicationResult;
    }

}
