package com.halil.finalhomework.domain.loanapplication.applicationrules;


import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("LoanApplicationFirstRule")
public class LoanApplicationFirstRule implements LoanApplicationRules{
    @Override
    public LoanApplicationResult calculateLoanApplicationResult(LoanApplication loanApplication,Integer creditLimitMultiplier) {
        LoanApplicationResult loanApplicationResult = new LoanApplicationResult();
        loanApplicationResult.setStatus(LoanApplicationStatus.DECLINED);
        loanApplicationResult.setLimit(0);
        loanApplicationResult.setCreatedDate(LocalDateTime.now());
        return loanApplicationResult;
    }
}
