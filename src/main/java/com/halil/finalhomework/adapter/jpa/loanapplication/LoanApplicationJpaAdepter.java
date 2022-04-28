package com.halil.finalhomework.adapter.jpa.loanapplication;

import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.member.Member;
import com.halil.finalhomework.domain.port.LoanApplicationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanApplicationJpaAdepter implements LoanApplicationPersistencePort {

    private final LoanApplicationJpaRepository loanApplicationJpaRepository;

    @Override
    public void createLoanApplication(Member member, LoanApplicationResult result) {
        LoanApplicationEntity entity = LoanApplicationEntity.convertToEntity(member,result);
        loanApplicationJpaRepository.save(entity);
    }

    @Override
    public List<LoanApplicationResult> retrieveLoanApplications(Long identityNumber){
        return loanApplicationJpaRepository.findAllByMember_IdentityNumber(identityNumber).stream()
                .map(LoanApplicationEntity::convertToResult)
                .collect(Collectors.toList());
    }
}


