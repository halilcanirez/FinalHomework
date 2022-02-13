package com.halil.finalhomework.adapter.jpa.loanapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationJpaRepository extends JpaRepository<LoanApplicationEntity, Long> {

    List<LoanApplicationEntity> findAllByMember_IdentityNumber(Long identityNumber);

}
