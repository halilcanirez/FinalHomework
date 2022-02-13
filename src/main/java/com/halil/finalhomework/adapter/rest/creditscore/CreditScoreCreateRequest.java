package com.halil.finalhomework.adapter.rest.creditscore;

import com.halil.finalhomework.domain.creditscore.CreditScore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditScoreCreateRequest {
    private Long identityNumber;
    private Integer score;

    public CreditScore convert(){
        CreditScore creditScore = new CreditScore();
        creditScore.setIdentityNumber(identityNumber);
        creditScore.setScore(score);
        return creditScore;
    }
}
