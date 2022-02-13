package com.halil.finalhomework.adapter.rest.loanapplication;

import com.halil.finalhomework.BaseIntegrationTest;
import com.halil.finalhomework.adapter.jpa.loanapplication.LoanApplicationEntity;
import com.halil.finalhomework.adapter.jpa.loanapplication.LoanApplicationJpaRepository;
import com.halil.finalhomework.adapter.rest.common.ExceptionResponse;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class LoanApplicationControllerTest extends BaseIntegrationTest {

    @Autowired
    LoanApplicationJpaRepository loanApplicationJpaRepository;

    @Test
    @Sql(scripts = "/create-credit-score.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_create_loan_application(){
        //given
        LoanApplicationCreateRequest request = LoanApplicationCreateRequest.builder()
                .identityNumber(35830993626L)
                .name("test name")
                .surname("test surname")
                .birthDate(LocalDate.of(1999,9,13))
                .phoneNumber("5342316214")
                .salary(5000)
                .build();
        //when
        ResponseEntity<LoanApplicationResponse> response = testRestTemplate.postForEntity("/loan-application", request, LoanApplicationResponse.class);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getLimit()).isNotNull();
        assertThat(response.getBody().getLimit()).isNotNull();

        List<LoanApplicationEntity> createdLoanApplications = loanApplicationJpaRepository.findAllByMember_IdentityNumber(35830993626L);
        assertThat(createdLoanApplications).
                hasSize(1)
                .extracting("id", "money" ,"status")
                .contains(
                        tuple(1L, 20000, LoanApplicationStatus.ACCEPTED)  //"status"
                );
    }
    @Test
    @Sql(scripts = "/create-credit-score.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_not_create_loan_application_when_phone_number_not_valid(){
        LoanApplicationCreateRequest request = LoanApplicationCreateRequest.builder()
                .identityNumber(35830993626L)
                .name("test name")
                .surname("test surname")
                .birthDate(LocalDate.of(1999,9,13))
                .phoneNumber("534231621")
                .salary(5000)
                .build();

        ResponseEntity<ExceptionResponse> response = testRestTemplate.postForEntity("/loan-application", request,ExceptionResponse.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .extracting("code", "message")
                .containsExactly(2002, "PHONE NUMBER IS NOT EQUAL TO 11");
    }

    @Test
    @Sql(scripts = "/create-credit-score.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_not_create_loan_application_member_not_adult(){
        LoanApplicationCreateRequest request = LoanApplicationCreateRequest.builder()
                .identityNumber(35830993626L)
                .name("test name")
                .surname("test surname")
                .birthDate(LocalDate.of(2020,9,13))
                .phoneNumber("534231621")
                .salary(5000)
                .build();

        ResponseEntity<ExceptionResponse> response = testRestTemplate.postForEntity("/loan-application", request,ExceptionResponse.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .extracting("code", "message")
                .containsExactly(2001, "NOT ADULT");
    }

}