package com.halil.finalhomework.adapter.creditscore;


import com.halil.finalhomework.domain.creditscore.CreditScore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "credit_score")
@Entity(name = "credit_score")
public class CreditScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long identityNumber;

    @Column(nullable = false)
    private Integer score;

    public static CreditScoreEntity convertToEntity(CreditScore creditScore){
        CreditScoreEntity creditScoreEntity = new CreditScoreEntity();
        creditScoreEntity.setIdentityNumber(creditScore.getIdentityNumber());
        creditScoreEntity.setScore(creditScore.getScore());
        return creditScoreEntity;
    }

}
