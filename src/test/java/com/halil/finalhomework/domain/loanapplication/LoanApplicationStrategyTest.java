package com.halil.finalhomework.domain.loanapplication;

import com.halil.finalhomework.domain.loanapplication.applicationrules.LoanApplicationFirstRule;
import com.halil.finalhomework.domain.loanapplication.applicationrules.LoanApplicationRules;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class LoanApplicationStrategyTest {

    LoanApplicationStrategy loanApplicationStrategy;

    @BeforeEach
    void setup(){
        loanApplicationStrategy=new LoanApplicationStrategy();
    }

    @Test
    void should_application_result_equal_first_rule(){
        //given
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.now())
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("5374282221")
                .salary(5000)
                .build();
        //when
        LoanApplicationResult loanApplicationResult = loanApplicationStrategy.calculateLoanApplicationResult(loanApplication, 300);
        //then
        assertThat(loanApplicationResult).isNotNull();
        assertThat(loanApplicationResult.getStatus()).isEqualTo(LoanApplicationStatus.DECLINED);
        assertThat(loanApplicationResult.getLimit()).isEqualTo(0);
    }

    @Test
    void should_application_result_equal_second_rule(){
        //given
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.now())
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("5374282221")
                .salary(4000)
                .build();
        //when
        LoanApplicationResult loanApplicationResult = loanApplicationStrategy.calculateLoanApplicationResult(loanApplication, 500);
        //then
        assertThat(loanApplicationResult).isNotNull();
        assertThat(loanApplicationResult.getStatus()).isEqualTo(LoanApplicationStatus.ACCEPTED);
        assertThat(loanApplicationResult.getLimit()).isEqualTo(10000);
    }

    @Test
    void should_application_result_equal_third_rule(){
        //given
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.now())
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("5374282221")
                .salary(6000)
                .build();
        //when
        LoanApplicationResult loanApplicationResult = loanApplicationStrategy.calculateLoanApplicationResult(loanApplication, 500);
        //then
        assertThat(loanApplicationResult).isNotNull();
        assertThat(loanApplicationResult.getStatus()).isEqualTo(LoanApplicationStatus.ACCEPTED);
        assertThat(loanApplicationResult.getLimit()).isEqualTo(20000);
    }

    @Test
    void should_application_result_equal_fourth_rule(){
        //given
        LoanApplication loanApplication=LoanApplication.builder()
                .birthDate(LocalDate.now())
                .identityNumber(35830993628L)
                .name("test name")
                .surname("test surname")
                .phoneNumber("5374282221")
                .salary(6000)
                .build();
        //when
        LoanApplicationResult loanApplicationResult = loanApplicationStrategy.calculateLoanApplicationResult(loanApplication, 1000);
        //then
        assertThat(loanApplicationResult).isNotNull();
        assertThat(loanApplicationResult.getStatus()).isEqualTo(LoanApplicationStatus.ACCEPTED);
        assertThat(loanApplicationResult.getLimit()).isEqualTo(24000);
    }

}