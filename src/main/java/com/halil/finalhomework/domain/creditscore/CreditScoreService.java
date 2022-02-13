package com.halil.finalhomework.domain.creditscore;

import com.halil.finalhomework.domain.member.Member;

public interface CreditScoreService {
    void createCreditScore(CreditScore creditScore);
    Integer retrieveMemberCreditScore(Long identityNumber);
}
