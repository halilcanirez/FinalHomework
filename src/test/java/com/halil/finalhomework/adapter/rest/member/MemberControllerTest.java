package com.halil.finalhomework.adapter.rest.member;

import com.halil.finalhomework.BaseIntegrationTest;
import com.halil.finalhomework.adapter.jpa.member.MemberEntity;
import com.halil.finalhomework.adapter.jpa.member.MemberJpaRepository;
import com.halil.finalhomework.adapter.rest.common.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

class MemberControllerTest extends BaseIntegrationTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void should_create_member() {
        //when
        MemberCreateRequest request= MemberCreateRequest.builder()
                .identityNumber(35830993623L)
                .name("test name")
                .surname("test surname")
                .birthDate(LocalDate.of(1999,9,13))
                .telephoneNumber("5342316214")
                .build();
        //given
        ResponseEntity<MemberResponse> response = testRestTemplate.exchange("/member", HttpMethod.POST, new HttpEntity<>(request), MemberResponse.class);
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMemberId()).isNotNull();

        Optional<MemberEntity> optionalMember = memberJpaRepository.findByIdentityNumber(35830993623L);
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get().getIdentityNumber()).isEqualTo(35830993623L);
        assertThat(optionalMember.get().getName()).isEqualTo("test name");
        assertThat(optionalMember.get().getSurname()).isEqualTo("test surname");
        assertThat(optionalMember.get().getTelephoneNumber()).isEqualTo("5342316214");
        assertThat(optionalMember.get().getBirthDate()).isEqualTo(LocalDate.of(1999,9,13));
    }

    @Test
    @Sql(scripts = "/member-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void retrieveMember() {

        //when
        ResponseEntity<MemberResponse> response = testRestTemplate.getForEntity("/member/35830993629",MemberResponse.class);

        //then
        assertThat(response).isNotNull();
        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMemberId()).isEqualTo(1001);
        assertThat(response.getBody().getMemberName()).isEqualTo("test name");
        assertThat(response.getBody().getMemberSurname()).isEqualTo("test surname");
    }

    @Test
    void should_NOT_retrieve_member() {
        //when
        ResponseEntity<ExceptionResponse> response = testRestTemplate.getForEntity("/member/35830993629",ExceptionResponse.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .extracting("code", "message")
                .containsExactly(1001, "MEMBER NOT FOUND");
    }
}