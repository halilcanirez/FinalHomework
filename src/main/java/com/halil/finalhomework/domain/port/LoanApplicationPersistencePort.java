package com.halil.finalhomework.domain.port;

import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.member.Member;

import java.util.List;

public interface LoanApplicationPersistencePort {

    void createLoanApplication(Member member, LoanApplicationResult result);
    List<LoanApplicationResult> retrieveLoanApplications(Long identityNumber);

}
