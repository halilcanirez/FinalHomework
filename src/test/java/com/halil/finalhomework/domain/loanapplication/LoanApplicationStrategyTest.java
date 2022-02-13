package com.halil.finalhomework.domain.loanapplication;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
class LoanApplicationStrategyTest {

     LoanApplicationStrategy loanApplicationStrategy;

     LoanApplicationProperties loanApplicationProperties=new LoanApplicationProperties(4);

    @BeforeEach
    void setup(){
        loanApplicationStrategy=new LoanApplicationStrategy(loanApplicationProperties);
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
        //mock

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