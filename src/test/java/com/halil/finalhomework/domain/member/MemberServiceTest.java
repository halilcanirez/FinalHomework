package com.halil.finalhomework.domain.member;

import com.halil.finalhomework.domain.exception.FinalHomeworkValidationException;
import com.halil.finalhomework.domain.port.MemberPersistencePort;
import com.halil.finalhomework.domain.port.RedisCachePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    MemberService memberService;

    @Mock
    MemberPersistencePort memberPersistencePort;
    @Mock
    RedisCachePort redisCachePort;

    @BeforeEach
    void setup(){
        memberService=new MemberService(memberPersistencePort,redisCachePort);
    }

    @Test
    void should_retrieve_member_when_cache_is_exist(){
        //mock
        Optional<Member> mockMember =Optional.ofNullable( Member.builder()
                .id(1L)
                .identityNumber(35830993628L)
                .name("halil can")
                .surname("irez")
                .telephoneNumber("5342316214")
                .birthDate(LocalDate.now())
                .build());

        when(redisCachePort.retrieveMember(35830993628L)).thenReturn(mockMember); // any

        //when
        Member retrievedMember = memberService.retrieveMember(35830993628L);

        //then
        assertThat(retrievedMember).isNotNull();
        assertThat(retrievedMember.getId()).isEqualTo(mockMember.get().getId());
        assertThat(retrievedMember.getIdentityNumber()).isEqualTo(35830993628L);

        verifyNoInteractions(memberPersistencePort);
        verifyNoMoreInteractions(redisCachePort);
    }
    @Test
    void should_retrieve_member_from_db_when_cache_empty(){
        //mock
        Optional<Member> optionalMember = Optional.empty();
        when(redisCachePort.retrieveMember(any())).thenReturn(optionalMember); // any
        Member mockMember = Member.builder()
                .id(1L)
                .identityNumber(35830993628L)
                .name("halil can")
                .surname("irez")
                .telephoneNumber("5342316214")
                .birthDate(LocalDate.now())
                .build();
        when(memberPersistencePort.retrieveMemberByIdentityNumber(35830993628L)).thenReturn(mockMember);
        doNothing().when(redisCachePort).createMember(mockMember);
        //when
        Member retrievedMember = memberService.retrieveMember(35830993628L);
        //then
        assertThat(retrievedMember).isNotNull();
        assertThat(retrievedMember.getId()).isEqualTo(1L);
        assertThat(retrievedMember.getIdentityNumber()).isEqualTo(35830993628L);
    }
    @Test
    void should_not_create_member_when_identity_number_already_use(){
        Member newMember = Member.builder()
                .id(1L)
                .identityNumber(35830993628L)
                .build();
        //mock
        when(memberPersistencePort.isIdentityNumberAlreadyExist(35830993628L)).thenReturn(Boolean.TRUE);
        //when

        Throwable exception = assertThrows(
                FinalHomeworkValidationException.class, () -> {
                    memberService.createMember(newMember);
                });
        //then
        verifyNoMoreInteractions(memberPersistencePort);
        assertEquals("THİS IDENTİTY NUMBER ALREADY USE ", exception.getMessage());
    }

    @Test
    void should_create_member(){
        // given
        Member newMember = Member.builder()
                .id(1L)
                .identityNumber(35830993628L)
                .build();
        //mock
        when(memberPersistencePort.createMember(any())).thenReturn(newMember);

        //when
        Member createdMember = memberService.createMember(newMember);
        //then
        verify(memberPersistencePort,times(1)).createMember(newMember);
        assertThat(createdMember).isNotNull();
        assertThat(createdMember.getIdentityNumber()).isEqualTo(35830993628L);
    }


}