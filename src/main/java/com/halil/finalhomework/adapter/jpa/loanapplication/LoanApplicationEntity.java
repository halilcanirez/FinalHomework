package com.halil.finalhomework.adapter.jpa.loanapplication;


import com.halil.finalhomework.adapter.jpa.member.MemberEntity;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationStatus;
import com.halil.finalhomework.domain.loanapplication.LoanApplicationResult;
import com.halil.finalhomework.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name = "loan_applications")
@Entity
public class LoanApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    private MemberEntity member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    @Column(nullable = false)
    private Integer money;

    public static LoanApplicationEntity convertToEntity(Member member, LoanApplicationResult applicationResults){
        LoanApplicationEntity applicationEntity= new LoanApplicationEntity();
        applicationEntity.setCreatedDate(applicationResults.getCreatedDate());
        applicationEntity.setMoney(applicationResults.getLimit());
        applicationEntity.setStatus(applicationResults.getStatus());
        applicationEntity.setMember(MemberEntity.convertToMemberEntity(member)); // todo covertor class
        return applicationEntity;
    }

    public LoanApplicationResult convertToResult(){
        LoanApplicationResult result = new LoanApplicationResult();
        result.setStatus(status);
        result.setLimit(money);
        result.setCreatedDate(createdDate);
        return result;
    }

}
