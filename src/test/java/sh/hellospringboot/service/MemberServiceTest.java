package sh.hellospringboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.hellospringboot.domain.Member;
import sh.hellospringboot.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        // 테스트 실행마다 객체의 변수를 초기화 해주는 걸로 보면 된다.
        // repository 생성하여 memberService 생성자를 통해 주입 --> DI의 개념
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        // AfterEach annotation: 각 테스트 메소드 실행이 끝날 때마다 실행될 함수
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("name1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 던져지는 예외 type을 검증함
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        // try catch 로 예외 발생을 검증함
//        try {
//            memberService.join(member2);
//            fail("예외가 발생하지 않았음, 발생해야 합니다.");
//        }catch (IllegalStateException e){
//            // 예외 발생됨
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}