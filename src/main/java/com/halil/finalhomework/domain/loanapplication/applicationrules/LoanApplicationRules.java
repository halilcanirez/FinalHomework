package com.halil.finalhomework.domain.loanapplication.applicationrules;


import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;

public interface LoanApplicationRules {
    LoanApplicationResult calculateLoanApplicationResult(LoanApplication loanApplication, Integer creditLimitMultiplier);
}
