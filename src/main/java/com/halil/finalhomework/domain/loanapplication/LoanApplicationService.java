package com.halil.finalhomework.domain.loanapplication;


import com.halil.finalhomework.domain.exception.FinalHomeworkValidationException;
import com.halil.finalhomework.domain.member.Member;
import com.halil.finalhomework.domain.port.CreditScorePersistencePort;
import com.halil.finalhomework.domain.port.LoanApplicationPersistencePort;
import com.halil.finalhomework.domain.port.MemberPersistencePort;
import com.halil.finalhomework.domain.port.SmsSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.halil.finalhomework.domain.loanapplication.LoanApplicationValidator.*;
import static com.halil.finalhomework.domain.loanapplication.LoanApplicationValidator.isPhoneNumberAcceptable;

@Service
@RequiredArgsConstructor
public class LoanApplicationService {

    private final CreditScorePersistencePort creditScorePersistencePort;
    private final LoanApplicationPersistencePort loanApplicationPersistencePort;
    private final MemberPersistencePort memberPersistencePort;
    private final LoanApplicationStrategy loanApplicationStrategy;
    private final SmsSenderPort smsSenderPort;

    public LoanApplicationResult createLoanApplication(LoanApplication loanApplication){
        applicationValidator(loanApplication);
        Member member = memberPersistencePort.retrieveOrCreateMember(loanApplication);
        Integer memberCreditScore = creditScorePersistencePort.retrieveMemberCreditScore(loanApplication.getIdentityNumber());
        LoanApplicationResult applicationResult = loanApplicationStrategy.calculateLoanApplicationResult(loanApplication, memberCreditScore);
        loanApplicationPersistencePort.createLoanApplication(member,applicationResult);
        smsSenderPort.sendMessage(applicationResult,loanApplication.getPhoneNumber());
        return applicationResult;
    }

    public List<LoanApplicationResult> retrieveLoanApplications(Long identityNumber){
        return loanApplicationPersistencePort.retrieveLoanApplications(identityNumber);
    }

    private void applicationValidator(LoanApplication loanApplication){
        ValidationResult validatorResult = isAdult()
                .and(isPhoneNumberAcceptable())
                .apply(loanApplication);

        if(validatorResult!=ValidationResult.SUCCESS){
            throw new FinalHomeworkValidationException(validatorResult.getExceptionType());
        }
    }
}
