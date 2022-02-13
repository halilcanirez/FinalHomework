package com.halil.finalhomework.domain.creditscore;


import com.halil.finalhomework.domain.port.CreditScorePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditScoreServiceImp implements CreditScoreService{

    private final CreditScorePersistencePort creditScorePersistencePort;

    @Override
    public void createCreditScore(CreditScore creditScore ){
        creditScorePersistencePort.save(creditScore);
    }

    @Override
    public Integer retrieveMemberCreditScore(Long identityNumber) {
        return creditScorePersistencePort.retrieveMemberCreditScore(identityNumber);
    }


}
