package com.halil.finalhomework.domain.port;

import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.member.Member;

public interface MemberPersistencePort {

    Member createMember(Member member);
    Member retrieveMemberByIdentityNumber(Long identityNumber);
    Member retrieveOrCreateMember(LoanApplication loanApplication);
}

