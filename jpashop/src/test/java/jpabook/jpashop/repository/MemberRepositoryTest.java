package jpabook.jpashop.repository;

import jpabook.jpashop.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("기초 테스트")
    public void testMember() {

        final Member member = new Member();
        member.setName("memberA");

        final Long savedId = memberRepository.save(member);

        final Member findMember = memberRepository.findOne(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    public void 쓰레드마다_다른_엔티티매니저() {
        memberRepository.save(new Member());
        memberRepository.save(new Member());
        memberRepository.save(new Member());
    }
}