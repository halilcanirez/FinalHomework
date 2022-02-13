package com.halil.finalhomework.domain.port;

import com.halil.finalhomework.domain.creditscore.CreditScore;

public interface CreditScorePersistencePort {
    void save(CreditScore creditScore);
    Integer retrieveMemberCreditScore(Long identityNumber);
}
