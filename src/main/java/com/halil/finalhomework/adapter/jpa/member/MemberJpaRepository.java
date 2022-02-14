package com.halil.finalhomework.adapter.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByIdentityNumber(Long identityNumber);
    Boolean existsByIdentityNumber(Long ıdentityNumber);

}
