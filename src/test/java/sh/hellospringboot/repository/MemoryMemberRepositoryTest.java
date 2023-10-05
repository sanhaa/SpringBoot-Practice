package sh.hellospringboot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sh.hellospringboot.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        // AfterEach annotation: 각 테스트 메소드 실행이 끝날 때마다 실행될 함수
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("myName");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

//        System.out.println("result = " + (result == member));

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("find1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("find2");
        repository.save(member2);

        Member result = repository.findByName("find1").get();
        assertThat(member1).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("hi1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hi2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("hi3");
        repository.save(member3);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }
}
