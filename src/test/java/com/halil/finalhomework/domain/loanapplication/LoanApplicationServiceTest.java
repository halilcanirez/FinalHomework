package com.halil.finalhomework.domain.loanapplication;

import com.halil.finalhomework.domain.creditscore.CreditScore;
import com.halil.finalhomework.domain.exception.FinalHomeworkValidationException;
import com.halil.finalhomework.domain.member.Member;
import com.halil.finalhomework.domain.port.CreditScorePersistencePort;
import com.halil.finalhomework.domain.port.LoanApplicationPersistencePort;
import com.halil.finalhomework.domain.port.MemberPersistencePort;
import com.halil.finalhomework.domain.port.SmsSenderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

    LoanApplicationService loanApplicationService;

    @Mock
    CreditScorePersistencePort creditScorePersistencePort;
    @Mock
    LoanApplicationPersistencePort loanApplicationPersistencePort;
    @Mock
    MemberPersistencePort memberPersistencePort;
    @Mock
    LoanApplicationStrategy loanApplicationStrategy;
    @Mock
    SmsSenderPort smsSenderPort;

    @BeforeEach
    void setup(){
        loanApplicationService= new LoanApplicationService(creditScorePersistencePort,
                loanApplicationPersistencePort,memberPersistencePort,loanApplicationStrategy, smsSenderPort);
    }

    @Test
    void should_create_loan_application() {
        //given
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.of(1999,9,13))
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("5374282221")
                .salary(5000)
                .build();

        //mock
        Member mockMember = Member.builder()
                .id(1L)
                .identityNumber(35830993628L)
                .telephoneNumber("5342316214")
                .birthDate(LocalDate.of(1999,9,13))
                .name("test name")
                .surname("test surname")
                .build();

        CreditScore creditScore= new CreditScore();
        creditScore.setScore(1000);
        creditScore.setIdentityNumber(35830993628L);
        LoanApplicationResult mockResult = new LoanApplicationResult();
        mockResult.setCreatedDate(LocalDateTime.now());
        mockResult.setLimit(20000);
        mockResult.setStatus(LoanApplicationStatus.ACCEPTED);

        when(memberPersistencePort.retrieveOrCreateMember(loanApplication)).thenReturn(mockMember);
        when(creditScorePersistencePort.retrieveMemberCreditScore(35830993628L)).thenReturn(1000);
        when(loanApplicationStrategy.calculateLoanApplicationResult(loanApplication,creditScore.getScore())).thenReturn(mockResult);
        //when
        LoanApplicationResult result = loanApplicationService.createLoanApplication(loanApplication);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(LoanApplicationStatus.ACCEPTED);
        assertThat(result.getLimit()).isEqualTo(20000);
        verify(loanApplicationPersistencePort,times(1)).createLoanApplication(mockMember,mockResult);
    }

    @Test
    void retrieveLoanApplications() {

        //mock
        LoanApplicationResult mockResult = new LoanApplicationResult();
        mockResult.setCreatedDate(LocalDateTime.now());
        mockResult.setLimit(20000);
        mockResult.setStatus(LoanApplicationStatus.ACCEPTED);
        List<LoanApplicationResult> loanApplicationResultList = List.of(
                mockResult
        );
        when(loanApplicationPersistencePort.retrieveLoanApplications(35830993628L)).thenReturn(loanApplicationResultList);
        //when
        List<LoanApplicationResult> loanApplicationResults = loanApplicationService.retrieveLoanApplications(35830993628L);
        //then
        assertThat(loanApplicationResultList).isNotEmpty();
    }

    @Test
    void should_return_validation_exception_if_not_acceptable_number() {
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.of(1999,9,13))
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("53742")
                .salary(5000)
                .build();
        Throwable exception = assertThrows(
                FinalHomeworkValidationException.class, () -> {
                    loanApplicationService.createLoanApplication(loanApplication);
                }
        );
        assertEquals("PHONE NUMBER IS NOT EQUAL TO 11", exception.getMessage());
    }

    @Test
    void should_return_validation_exception_when_member_not_adult() {
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.of(2020,9,13))
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("53742")
                .salary(5000)
                .build();

        Throwable exception = assertThrows(
                FinalHomeworkValidationException.class, () -> {
                    loanApplicationService.createLoanApplication(loanApplication);
                }
        );
        assertEquals("NOT ADULT", exception.getMessage());
    }
}