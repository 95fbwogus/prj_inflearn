package com.siloam.home.repository.member;

import com.siloam.home.domain.member.Member;
import com.siloam.home.domain.member.impl.SiloamMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public List<SiloamMember> findMember(String code) {
        return em.createQuery("select m from SiloamMember m", SiloamMember.class)
                .getResultList();
    }

}
