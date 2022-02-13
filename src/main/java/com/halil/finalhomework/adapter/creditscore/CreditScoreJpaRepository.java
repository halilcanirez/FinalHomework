package com.halil.finalhomework.adapter.creditscore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreJpaRepository extends JpaRepository<CreditScoreEntity,Long> {

    Optional<CreditScoreEntity> findByIdentityNumber(Long identityNumber);
}
