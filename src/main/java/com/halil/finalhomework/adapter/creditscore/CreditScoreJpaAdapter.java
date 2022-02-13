package com.halil.finalhomework.adapter.creditscore;

import com.halil.finalhomework.domain.creditscore.CreditScore;
import com.halil.finalhomework.domain.exception.ExceptionType;
import com.halil.finalhomework.domain.exception.FinalHomeworkDataNotFoundException;
import com.halil.finalhomework.domain.port.CreditScorePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditScoreJpaAdapter implements CreditScorePersistencePort {

    private final CreditScoreJpaRepository creditScoreJpaRepository;

    @Override
    public void save(CreditScore creditScore) {
        CreditScoreEntity entity =CreditScoreEntity.convertToEntity(creditScore);
        creditScoreJpaRepository.save(entity);
    }

    @Override
    public Integer retrieveMemberCreditScore(Long identityNumber) {
        return creditScoreJpaRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new FinalHomeworkDataNotFoundException(ExceptionType.CREDIT_SCORE_DATA_NOT_FOUND))
                .getScore();
    }
}
