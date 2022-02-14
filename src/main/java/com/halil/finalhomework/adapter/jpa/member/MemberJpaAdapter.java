package com.halil.finalhomework.adapter.jpa.member;

import com.halil.finalhomework.domain.exception.ExceptionType;
import com.halil.finalhomework.domain.exception.FinalHomeworkDataNotFoundException;
import com.halil.finalhomework.domain.loanapplication.LoanApplication;
import com.halil.finalhomework.domain.member.Member;
import com.halil.finalhomework.domain.port.MemberPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberPersistencePort {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member createMember(Member member) {
        return memberJpaRepository.save(MemberEntity.convertToMemberEntity(member)).convertToMember();
    }

    @Override
    public Member retrieveMemberByIdentityNumber(Long identityNumber) {
        Optional<MemberEntity> entity =memberJpaRepository.findByIdentityNumber(identityNumber);
        return entity.orElseThrow(() -> new FinalHomeworkDataNotFoundException(ExceptionType.MEMBER_DATA_NOT_FOUND))
                .convertToMember();
    }

    @Override
    public Member retrieveOrCreateMember(LoanApplication loanApplication){
        // if member exist then retrieve existing member, if not create new member
        Optional<MemberEntity> entity =memberJpaRepository.findByIdentityNumber(loanApplication.getIdentityNumber());

        if (entity.isEmpty()){
            MemberEntity memberEntity= getEntityFromLoanApplication(loanApplication);
            return memberJpaRepository.save(memberEntity).convertToMember();
        }
        return entity.get().convertToMember();
    }

    @Override
    public Boolean isIdentityNumberAlreadyExist(Long identityNumber) {
        return memberJpaRepository.existsByIdentityNumber(identityNumber);
    }

    @Override
    public Member updateMemberById(Member member) {

        MemberEntity memberEntity = MemberEntity.convertToMemberEntity(member);
        return memberJpaRepository.save(memberEntity).convertToMember();
    }

    @Override
    public void updateStatusById(Long memberId){
        Optional<MemberEntity> entity =memberJpaRepository.findById(memberId);
        if (entity.isEmpty()){
            throw new FinalHomeworkDataNotFoundException(ExceptionType.MEMBER_DATA_NOT_FOUND);
        }
        MemberEntity entity2 = entity.get();
        entity2.setStatus(Status.DELETED);
        memberJpaRepository.save(entity2);
    }

    private MemberEntity getEntityFromLoanApplication(LoanApplication loanApplication){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIdentityNumber(loanApplication.getIdentityNumber());
        memberEntity.setName(loanApplication.getName());
        memberEntity.setSurname(loanApplication.getSurname());
        memberEntity.setBirthDate(loanApplication.getBirthDate());
        memberEntity.setTelephoneNumber(loanApplication.getPhoneNumber());
        return memberEntity;
    }



}
