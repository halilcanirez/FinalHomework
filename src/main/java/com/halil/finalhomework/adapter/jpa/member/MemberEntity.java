package com.halil.finalhomework.adapter.jpa.member;

import com.halil.finalhomework.domain.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "member")
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long identityNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String telephoneNumber;

    public static MemberEntity convertToMemberEntity(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(member.getId());
        memberEntity.setIdentityNumber(member.getIdentityNumber());
        memberEntity.setName(member.getName());
        memberEntity.setSurname(member.getSurname());
        memberEntity.setTelephoneNumber(member.getTelephoneNumber());
        memberEntity.setBirthDate(member.getBirthDate());
        memberEntity.setStatus(Status.ACTIVE);
        return memberEntity;
    }

    public Member convertToMember(){
        return Member.builder()
                .id(id)
                .identityNumber(identityNumber)
                .name(name)
                .surname(surname)
                .birthDate(birthDate)
                .telephoneNumber(telephoneNumber)
                .build();
    }
}
