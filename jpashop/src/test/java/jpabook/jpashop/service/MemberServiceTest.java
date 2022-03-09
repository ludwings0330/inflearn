package jpabook.jpashop.service;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        final Member member = new Member();
        member.setName("bae");
        //when
        final Long savedId = memberService.join(member);
        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        final Member memberA = new Member();
        memberA.setName("bae");

        final Member memberB = new Member();
        memberB.setName("bae");

        memberService.join(memberA);

        Assertions.assertThatThrownBy(() -> memberService.join(memberB))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 회원");
    }
}